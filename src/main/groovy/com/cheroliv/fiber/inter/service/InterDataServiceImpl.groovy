package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.InterUtils
import com.cheroliv.fiber.inter.domain.enumeration.InterContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.InterTypeEnum
import com.cheroliv.fiber.inter.repository.InterRepository
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j
import org.apache.commons.io.FilenameUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

import static com.cheroliv.fiber.inter.domain.InterConstants.*

@Slf4j
@Service
@TypeChecked
@Transactional(readOnly = true)
class InterDataServiceImpl implements InterDataService {


    final InterRepository interRepository
    final String homeDirectoryName
    final String jsonBackupFileName
    final Resource resourceFile

    InterDataServiceImpl(
            @Value('${application.data.home-directory-name}')
                    String homeDirectoryName,
            @Value('${application.data.json-backup-file-name}')
                    String jsonBackupFileName,
            @Value("classpath:inters.json")
                    Resource resourceFile,
            InterRepository interRepository) {
        this.interRepository = interRepository
        this.homeDirectoryName = homeDirectoryName
        this.jsonBackupFileName = jsonBackupFileName
        this.resourceFile = resourceFile
    }

    @Override
    String getJsonBackupFilePath() {
        System.getProperty('user.home') +
                System.getProperty('file.separator') +
                this.homeDirectoryName +
                System.getProperty('file.separator') +
                this.jsonBackupFileName
    }


    @Override
    Inter find(String nd, String type) {
        Optional<Inter> result = this.interRepository.find(
                nd, InterTypeEnum.valueOfName(type))
        if (result.present)
            result.get()
        else throw new InterNotFoundException("nd : $nd, type:$type")
    }


    @Override
    Integer countMois() {
        interRepository.distinctMoisParAnnee()?.size() ?: 0
    }

    @Override
    List<Map<String, Integer>> findAllMoisFormatFrParAnnee() {
        List<List<Integer>> result = interRepository.distinctMoisParAnnee()
        List<Map<String, Integer>> finalResult =
                new ArrayList<Map<String, Integer>>(result.size())
        result.eachWithIndex { List<Integer> item, int idx ->
            Integer intMois = item.get(0)
            Integer annee = item.get(1)
            Map<String, Integer> map = new HashMap<String, Integer>(1)
            map[InterUtils.convertNombreEnMois(intMois)] = annee
            finalResult.add(idx, map)
        }
        finalResult
    }


    private static ZonedDateTime buildDateTime(String strDate, String strHour) {
        LocalDateTime localDateTime = LocalDateTime.of(
                InterUtils.parseStringDateToLocalDate(strDate),
                InterUtils.parseStringHeureToLocalTime(strHour))
        ZonedDateTime.of(localDateTime, ZoneId.systemDefault())
    }


    private static InterContractEnum parseI18nInterContractEnum(String i18nContactValue) {
        InterContractEnum.valueOfName(
                i18nContactValue ==
                        'Passage de cable' ?
                        InterContractEnum.CABLE_ROUTING.name() :
                        i18nContactValue)
    }

    private void importJson(Map<String, String> it) {
        interRepository.find(
                it[ND_INTER_JSON_FIELD_NAME],
                InterTypeEnum.valueOfName(
                        it[TYPE_INTER_JSON_FIELD_NAME])) ?:
                interRepository.save(new Inter(
                        nd: it[ND_INTER_JSON_FIELD_NAME],
                        lastNameClient: it[LASTNAME_INTER_JSON_FIELD_NAME],
                        firstNameClient: it[FIRSTNAME_INTER_JSON_FIELD_NAME],
                        dateTimeInter: buildDateTime(
                                it[DATE_INTER_JSON_FIELD_NAME],
                                it[HOUR_INTER_JSON_FIELD_NAME]),
                        contract: parseI18nInterContractEnum(
                                it[CONTRACT_INTER_JSON_FIELD_NAME]),
                        typeInter: InterTypeEnum.valueOfName(it[TYPE_INTER_JSON_FIELD_NAME])))

    }


    @Transactional
    void importJsonFromFile() {
        Object jsonInters = new JsonSlurper()
                .parseText(resourceFile
                        .getFile()
                        .getText(StandardCharsets.UTF_8.name()))
        (jsonInters as List<Map<String, String>>)
                .each { Map<String, String> it ->
                    if (!it.isEmpty()) this.importJson(it)
                }
    }

    @Override
    @TypeChecked(TypeCheckingMode.SKIP)
    String buildJsonInter(Inter inter) {
        JsonBuilder builder = new JsonBuilder()
        String result =
                builder {
                    '"id_inter"' "\"${inter.id}\""
                    '"ND"' "\"${inter.nd}\""
                    '"nom"' "\"${inter.lastNameClient}\""
                    '"prenom"' "\"${inter.firstNameClient}\""
                    '"heure"' inter.dateTimeInter.getHour() > 9 ?
                            "\"${inter.dateTimeInter.getHour()}:00:00\"" :
                            "\"0${inter.heure.hour}:00:00\""
                    '"date"' "\"${inter.dateTimeInter.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))}\""
                    '"contrat"' "\"${inter.contract.name()}\""
                    '"type"' "\"${inter.typeInter.name()}\""
                }.toString()
        "{${result.substring(1, result.size() - 1)}}".toString()
    }


    @Override
    void saveToJsonFile() {
        List<String> jsonList = new ArrayList<String>()

        //building json
        interRepository.findAll().each { Inter inter ->
            jsonList.add "${buildJsonInter(inter)}\n".toString()
        }

        //renaming file
        String ajout = new SimpleDateFormat("yyyyMMddHHmmss")
                .format new Date()
        new File(this.getJsonBackupFilePath()).renameTo(
                "${FilenameUtils.removeExtension this.getJsonBackupFilePath()}" +
                        "${ajout}.json")
        //saving to file
        File jsonBackUpFile = new File(this.getJsonBackupFilePath())
        jsonBackUpFile.createNewFile()
        jsonBackUpFile.setText(jsonList.toListString(), StandardCharsets.UTF_8.toString())
    }
}


