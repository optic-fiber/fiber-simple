package com.cheroliv.fiber.repository

import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.InterConstants
import com.cheroliv.fiber.inter.domain.InterUtils
import com.cheroliv.fiber.inter.domain.enumeration.InterContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.InterTypeEnum
import com.cheroliv.fiber.inter.repository.InterRepository
import groovy.json.JsonSlurper
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.ApplicationContext
import org.springframework.core.io.Resource
import org.springframework.transaction.annotation.Transactional

import javax.validation.Validator
import java.time.*
import java.time.format.DateTimeFormatter

@Slf4j
@TypeChecked
abstract class AbstractInterTestCase {

    @Autowired
    ApplicationContext applicationContext
    @Autowired
    InterRepository interRepository
    @Autowired
    Validator validator
    @Value('${application.data.home-directory-name}')
    String homeDirectoryName
    @Value('${application.data.json-backup-file-name}')
    String jsonBackupFileName
    @Value("classpath:inters.json")
    Resource resourceFile

    static Inter jsonDataToInter(Map<String, String> strJsonData) {
        ZoneId zone = ZoneId.systemDefault()
        ZoneOffset zoneOffSet = zone
                .getRules()
                .getOffset(LocalDateTime.now())

        LocalDateTime localDateTime = LocalDateTime.of( /*LocalDate.parse(strJsonData[InterConstants.DATE_INTER_JSON_FIELD_NAME],
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")*/
                InterUtils.parseStringDateToLocalDate(strJsonData[InterConstants.DATE_INTER_JSON_FIELD_NAME]),
                InterUtils.parseStringHeureToLocalTime(strJsonData[InterConstants.HOUR_INTER_JSON_FIELD_NAME]))
        ZonedDateTime dateTime = ZonedDateTime.ofLocal(localDateTime, zone, zoneOffSet)

        new Inter(
                id: Long.parseLong(strJsonData[InterConstants.ID_INTER_JSON_FIELD_NAME]),
                nd: strJsonData[InterConstants.ND_INTER_JSON_FIELD_NAME],
                lastNameClient: strJsonData[InterConstants.LASTNAME_INTER_JSON_FIELD_NAME],
                firstNameClient: strJsonData[InterConstants.FIRSTNAME_INTER_JSON_FIELD_NAME],
                dateTimeInter: dateTime,
                contract: InterContractEnum.valueOfName(
                        strJsonData[InterConstants.CONTRACT_INTER_JSON_FIELD_NAME] == InterConstants.PASSAGE_DE_CABLE ?
                                InterContractEnum.CABLE_ROUTING.name() : strJsonData[InterConstants.CONTRACT_INTER_JSON_FIELD_NAME]),
                typeInter: InterTypeEnum.valueOfName(strJsonData[InterConstants.TYPE_INTER_JSON_FIELD_NAME]))
    }

    void setUp() {
        populateDB()
    }


    List<Map<String, String>> getJsonData() {
        new JsonSlurper()?.parseText(resourceFile
                .file.getText('utf-8')
        ) as List<Map<String, String>>
    }


    List<LocalDate> getAllDates() {
        List<LocalDate> dates = new ArrayList<LocalDate>()
        this.getJsonData().each { Map<String, String> it ->
            dates.add LocalDate.parse(
                    it[InterConstants.DATE_INTER_JSON_FIELD_NAME],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"))
        }
        dates
    }

    List<Map<Integer, Integer>> getAllMapDates() {
        List<Map<Integer, Integer>> dateMaps = new ArrayList<Map<Integer, Integer>>()
        this.getAllDates().each { LocalDate date ->
            dateMaps.add([(date.getMonthValue()): date.getYear()])
        }
        dateMaps
    }

    List<Map<Integer, Integer>> getAnneesMoisDistinctAsMap() {
        List<Map<Integer, Integer>> map = new ArrayList<Map<Integer, Integer>>()
        List<Map<Integer, Integer>> allMap = this.getAllMapDates()
        Map<Integer, Integer> lastFound = allMap.first()
        map.add(lastFound)
        allMap.each { Map<Integer, Integer> it ->
            if (it != lastFound && !map.contains(it)) {
                map.add(it)
                lastFound = it
            }
        }
        map
    }

    List<List<Integer>> getAnneesMoisDistinct() {
        List list = []
        this.getAnneesMoisDistinctAsMap().each { Map<Integer, Integer> it ->
            assert it.size() == 1
            list.add([it.keySet().first(),
                      it.getAt(it.keySet().first())])
        }
        list
    }


    @Transactional
    void populateDB() {
        if (interRepository?.count() == 0)
            jsonData.each {
                Inter inter = jsonDataToInter(it)
                if (!validator.validate(inter).empty)
                    validator.validate(inter).each {
                        log.info it.message
                        log.info inter.toString()
                    }
                else interRepository.save inter
            }
    }


    @Transactional
    void truncateDB() {
        interRepository.deleteAll()
    }
}
