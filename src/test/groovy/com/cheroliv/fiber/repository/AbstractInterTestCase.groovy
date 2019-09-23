package com.cheroliv.fiber.repository

//import com.cheroliv.fiber.inter.domain.Inter
//import com.cheroliv.fiber.inter.domain.InterUtils
//import groovy.json.JsonSlurper
//import groovy.transform.CompileStatic
//import groovy.util.logging.Slf4j
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.context.ApplicationContext
//import org.springframework.core.io.Resource
//import org.springframework.transaction.annotation.Transactional
//
//import javax.validation.Validator
//import java.nio.file.Paths
//import java.time.LocalDate
//import java.time.LocalTime
//import java.time.ZoneId
//import java.time.ZonedDateTime
//import java.time.format.DateTimeFormatter
//
//@Slf4j
//@CompileStatic
abstract class AbstractInterTestCase {

//    @Autowired
//    ApplicationContext applicationContext
//
//    @Autowired
//    InterRepository interRepository
//
//    @Autowired
//    Validator validator
//
//    @Value('${fiberUserDataFolderName}')
//    String fiberUserDataFolderName
//    @Value('${jsonBackUpFileName}')
//    String jsonBackUpFileName
//    @Value('${classeurPathName}')
//    String classeurPathName
//    @Value('${classeurDirectoryName}')
//    String classeurDirectoryName
//
//
//    String getRootPathData() {
//        new File(applicationContext
//                .getResource("/").URI).path
//    }
//
//
//    Inter jsonDataToInter(Map<String, String> strJsonData) {
//        new Inter(
//                id: Long.parseLong (strJsonData["id_inter"]),
//                nd: strJsonData["ND"],
//                nom: strJsonData["nom"],
//                prenom: strJsonData["prenom"],
//                heure: InterUtils.parseStringHeureToLocalTime(strJsonData["heure"]),
//                date: LocalDate.parse(strJsonData["date"], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                contrat: strJsonData["contrat"],
//                type: strJsonData["type"])
//    }
//
//
//    Resource getClasseurResource() {
//        String rootPathData = new File(applicationContext
//                .getResource("/").URI).path
//        applicationContext
//                .getResource("file:" +
//                        "${rootPathData}${separator}" +
//                        "${fiberUserDataFolderName}${separator}" +
//                        "${classeurDirectoryName}${separator}" +
//                        "$classeurPathName")
//    }
//
//    String getJsonDatasetPath() {
//        applicationContext
//                .getResource("classpath:inter.json")
//                .getFile()
//                .toPath()
//                .toString()
//    }
//
//    void setUp() {
//        populateDB()
//    }
//
//
//    static String getSeparator() {
//        Paths.get(System.getProperty("user.home"))
//                .fileSystem.separator
//    }
//
//    List<Map<String, String>> getJsonData() {
//        new JsonSlurper()?.parseText(
//                applicationContext
//                        .getResource("classpath:inter.json")
//                        .file.getText('utf-8')
//        ) as List<Map<String, String>>
//    }
//
//    List<List<Integer>> getAnneesMois() {
//        //recuperer le resultat depuis le jeux de données json
//        // et nom depuis une requetes de spring data
//        List<LocalDate> dates = new ArrayList<LocalDate>()
//        List<Map<String, String>> data = jsonData
//        data.each {
//            dates.add LocalDate.parse(
//                    it["date"],
//                    DateTimeFormatter.ofPattern("yyyy-MM-dd"))
//        }
//        //combien d'annees?
//        List<Integer> years = new ArrayList<Integer>()
//        years.add dates.first().year
//        Integer lastYearAdd = years.first()
//        dates.each { date ->
//            if (date.year != lastYearAdd) {
//                Boolean yearFound = Boolean.FALSE
//                years.each { year ->
//                    if (year == date.year) yearFound = Boolean.TRUE
//                }
//                if (!yearFound) {
//                    years.add date.year
//                    lastYearAdd = date.year
//                }
//            }
//        }
//        //Combien de mois par annees
//        List<List<Integer>> months = new ArrayList<List<Integer>>()
//        months.add([dates.first().monthValue, years.first()] as ArrayList<Integer>)
//        Integer lastMonthAdd = dates.first().monthValue
//
//        years.eachWithIndex { year, idx ->
//            dates.each { date ->
//                if (date.year == year && date.monthValue != lastMonthAdd) {
//                    Boolean monthFound = Boolean.FALSE
//                    months.get(idx).each { month ->
//                        if (month == date.monthValue) {
//                            monthFound = Boolean.TRUE
//                        }
//                    }
//                    if (!monthFound) {
//                        months.add([date.monthValue, year])
//                        lastMonthAdd = date.monthValue
//                    }
//                }
//            }
//        }
//        months
//    }
//
//    List<Map<String, Integer>> getMoisFormatFrParAnnee() {
//        List<List<Integer>> anneesMoisData = anneesMois
//        List<Map<String, Integer>> finalResult =
//                new ArrayList<Map<String, Integer>>(anneesMoisData.size())
//        //todo:handle if date is null on db
//        anneesMoisData.eachWithIndex { item, idx ->
//            Integer intMois = item.get 0
//            Integer annee = item.get 1
//            Map<String, Integer> map = new HashMap<String, Integer>(1)
//            map[InterUtils.convertNombreEnMois(intMois)] = annee
//            finalResult.add idx, map
//        }
//        finalResult
//    }
//
//    @Transactional
//    void populateDB() {
//        if (interRepository?.count() == 0) {
//            jsonData.each {
//                Inter inter = new Inter(
//                        nd: it["ND"],
//                        nom: it["nom"],
//                        prenom: it["prenom"],
//                        heure: InterUtils.parseStringHeureToLocalTime(it["heure"]),
//                        date: LocalDate.parse(it["date"], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
//                        contrat: it["contrat"],
//                        type: it["type"])
//                if (!validator.validate(inter).empty) {
//                    validator.validate(inter).each {
//                        log.info it.message
//                        log.info inter.toString()
//                    }
//                } else interRepository.save inter
//            }
//        }
//    }
//
//    @Transactional
//    void truncateDB() {
//        interRepository.deleteAll()
//    }
}
