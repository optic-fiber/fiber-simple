package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.dao.InterDao
import com.cheroliv.fiber.inter.dao.InterventionRepository
import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.InterUtils
import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.inter.dto.InterDto
import com.cheroliv.fiber.inter.service.exceptions.InterEntityNotFoundException
import com.cheroliv.fiber.inter.service.exceptions.InterTypeEnumException
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import groovy.transform.TypeChecked
import groovy.transform.TypeCheckingMode
import groovy.util.logging.Slf4j
import org.apache.commons.io.FilenameUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.nio.charset.StandardCharsets
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import static com.cheroliv.fiber.config.ApplicationConstants.*
import static com.cheroliv.fiber.inter.domain.InterConstants.*

@Slf4j
@Service
@TypeChecked
@Transactional(readOnly = true)
class InterDataServiceImpl implements InterDataService {

    final InterDao dao
    final String homeDirectoryName
    final String jsonBackupFileName

    InterDataServiceImpl(
            @Value(KEY_DATA_HOME_DIRECTORY)
                    String homeDirectoryName,
            @Value(KEY_DATA_JSON_BACKUP_FILE_NAME)
                    String jsonBackupFileName,
            InterDao dao) {
        this.dao = dao
        this.homeDirectoryName = homeDirectoryName
        this.jsonBackupFileName = jsonBackupFileName
    }

    private void createHomeDataDirectory() {
        File file = new File(System.getProperty(KEY_SYSTEM_PROPERTY_USER_HOME) +
                System.getProperty(KEY_SYSTEM_PROPERTY_FILE_SEPARATOR) +
                homeDirectoryName)
        if (file.exists()) {
            if (file.isFile()) {
                file.delete()
                file.mkdir()
            }
        } else file.mkdir()
    }

    private void createJsonFile() {
        createHomeDataDirectory()
        File file = new File(System.getProperty(KEY_SYSTEM_PROPERTY_USER_HOME) +
                System.getProperty(KEY_SYSTEM_PROPERTY_FILE_SEPARATOR) +
                homeDirectoryName +
                System.getProperty(KEY_SYSTEM_PROPERTY_FILE_SEPARATOR) +
                this.jsonBackupFileName
        )
        if (!(file.exists() && file.isFile()))
            if (file.exists() && file.isDirectory()) {
                file.deleteDir()
                file.createNewFile()
            } else file.createNewFile()
    }

    @Override
    String getJsonBackupFilePath() {
        createHomeDataDirectory()
        createJsonFile()
        System.getProperty(KEY_SYSTEM_PROPERTY_USER_HOME) +
                System.getProperty(KEY_SYSTEM_PROPERTY_FILE_SEPARATOR) +
                this.homeDirectoryName +
                System.getProperty(KEY_SYSTEM_PROPERTY_FILE_SEPARATOR) +
                this.jsonBackupFileName
    }


    @Override
    Inter find(String nd, String type) {
        if (!TypeInterEnum.values().collect {
            it.name()
        }.contains(type)) throw new InterTypeEnumException(type)
        Optional<Inter> result = this.dao.find(
                nd, TypeInterEnum.valueOfName(type))
        if (result.present)
            result.get()
        else throw new InterEntityNotFoundException(nd, type)
    }


    @Override
    Integer countMois() {
        dao.distinctMoisParAnnee()?.size() ?: 0
    }

    @Override
    List<Map<String, Integer>> findAllMoisFormatFrParAnnee() {
        List<List<Integer>> result = dao
                .distinctMoisParAnnee()
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


    private LocalDateTime buildDateTime(String strDate, String strHour) {
        LocalDateTime.of(
                InterUtils.parseStringDateToLocalDate(strDate),
                InterUtils.parseStringHeureToLocalTime(strHour))
    }

    private ContractEnum parseI18nInterContractEnum(String i18nContactValue) {
        ContractEnum.valueOfName(
                i18nContactValue ==
                        //TODO:change with a message bean handling i18n
                        'Passage de cable' ?
                        ContractEnum.CABLE_ROUTING.name() :
                        i18nContactValue)
    }

    private void importJson(Map<String, String> it) {
        dao.find(
                it[ND_INTER_JSON_FIELD_NAME],
                TypeInterEnum.valueOfName(
                        it[TYPE_INTER_JSON_FIELD_NAME])) ?:
                dao.save(new Inter(
                        nd: it[ND_INTER_JSON_FIELD_NAME],
                        lastNameClient: it[LASTNAME_INTER_JSON_FIELD_NAME],
                        firstNameClient: it[FIRSTNAME_INTER_JSON_FIELD_NAME],
                        dateTimeInter: buildDateTime(
                                it[DATE_INTER_JSON_FIELD_NAME],
                                it[HOUR_INTER_JSON_FIELD_NAME]),
                        contract: parseI18nInterContractEnum(
                                it[CONTRACT_INTER_JSON_FIELD_NAME]),
                        typeInter: TypeInterEnum.valueOfName(it[TYPE_INTER_JSON_FIELD_NAME])))

    }


    @Transactional
    void importJsonFromFile() {
        assert new File(getJsonBackupFilePath()).exists()
        if (!(new File(getJsonBackupFilePath()).text == null
                || new File(getJsonBackupFilePath()).text == ""
                || new File(getJsonBackupFilePath()).text.empty)) {
            Object jsonInters = new JsonSlurper()
                    .parseText(new File(getJsonBackupFilePath())
                            .getText(StandardCharsets.UTF_8.name()))
            (jsonInters as Collection).empty ?:
                    (jsonInters as List<Map<String, String>>)
                            .each { Map<String, String> it ->
                                if (!it.isEmpty()) this.importJson(it)
                            }
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
        dao.findAll().each { Inter inter ->
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


