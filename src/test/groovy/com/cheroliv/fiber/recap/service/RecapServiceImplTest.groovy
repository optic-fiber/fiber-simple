package com.cheroliv.fiber.recap.service

//import com.cheroliv.fiber.inter.dao.InterDao
//import com.cheroliv.fiber.inter.domain.InterConstants
//import com.cheroliv.fiber.inter.domain.InterUtils
//import com.cheroliv.fiber.inter.domain.InterventionEntity
//import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
//import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
//import com.cheroliv.fiber.inter.dto.InterventionDto
//import com.cheroliv.fiber.inter.service.InterDataService
//import com.cheroliv.fiber.recap.model.Recap
//import com.cheroliv.fiber.recap.spreadsheet.SpreadsheetRecap
//import com.google.common.collect.Maps
//import groovy.json.JsonSlurper
//import groovy.transform.CompileStatic
//import groovy.util.logging.Slf4j
//import org.junit.jupiter.api.*
//import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.context.ApplicationContext
//import org.springframework.context.ApplicationContextAware
//import org.springframework.core.io.Resource
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.transaction.annotation.Transactional
//
//import javax.validation.Validator
//import java.nio.charset.StandardCharsets
//import java.time.LocalDateTime
//import java.time.Month
//import java.time.format.TextStyle

//@Slf4j
//@CompileStatic
//@SpringBootTest
//@ActiveProfiles("test")
//@TestMethodOrder(OrderAnnotation)
class RecapServiceImplTest {
//    @Autowired
//    RecapService recapService
//    @Autowired
//    InterDataService interDataService
//    @Autowired
//    InterDao dao
//    @Autowired
//    ApplicationContext applicationContext
//    @Autowired
//    Validator validator
//    @Value('classpath:inters.json')
//    Resource jsonFileResource
//    @Value('${application.data.home-directory-name}')
//    String homeDirectoryName
//    String classeurPathName = 'recap_date-time1_date-time2.xlsx'
//    @Value('classpath:recap_date-time1_date-time2.xlsx')
//    Resource classeurResource
//
//    @BeforeEach
//    void setUp() {
//        populateDB()
//    }
//
//    @Transactional
//    void populateDB() {
//        if (dao.count() == 0)
//            jsonData.each {
//                InterventionEntity inter = jsonDataToInter(it)
//                if (!validator.validate(inter).empty)
//                    validator.validate(inter).each {
//                        log.info it.message
//                        log.info inter.toString()
//                    }
//                else dao.save inter
//            }
//    }
//
//    static InterventionEntity jsonDataToInter(Map<String, String> strJsonData) {
//        LocalDateTime localDateTime = LocalDateTime.of(
//                InterUtils.parseStringDateToLocalDate(strJsonData[InterConstants.DATE_INTER_JSON_FIELD_NAME]),
//                InterUtils.parseStringHeureToLocalTime(strJsonData[InterConstants.HOUR_INTER_JSON_FIELD_NAME]))
//
//        new InterventionEntity(
//                id: Long.parseLong(strJsonData[InterConstants.ID_INTER_JSON_FIELD_NAME]),
//                nd: strJsonData[InterConstants.ND_INTER_JSON_FIELD_NAME],
//                lastNameClient: strJsonData[InterConstants.LASTNAME_INTER_JSON_FIELD_NAME],
//                firstNameClient: strJsonData[InterConstants.FIRSTNAME_INTER_JSON_FIELD_NAME],
//                dateTimeInter: localDateTime,
//                contract: ContractEnum.valueOfName(
//                        strJsonData[InterConstants.CONTRACT_INTER_JSON_FIELD_NAME] == InterConstants.PASSAGE_DE_CABLE ?
//                                ContractEnum.CABLE_ROUTING.name() : strJsonData[InterConstants.CONTRACT_INTER_JSON_FIELD_NAME]),
//                typeInter: TypeInterEnum.valueOfName(strJsonData[InterConstants.TYPE_INTER_JSON_FIELD_NAME]))
//    }
//
//    List<Map<String, String>> getJsonData() {
//        new JsonSlurper()?.parseText(jsonFileResource
//                .file.getText(StandardCharsets.UTF_8.name())
//        ) as List<Map<String, String>>
//    }
//
//    @Test
//    @Order(1)
//    void testNomFeuilles() {
//        Integer countMois = interDataService.countMois()
//        List<Map<String, Integer>> list = interDataService.findAllMoisFormatFrParAnnee()
//        List<String> finalList = new ArrayList(countMois)
//        list.eachWithIndex { item, idx ->
//            String key = (item as Map<String, Integer>).keySet().first()
//            String value = (item as Map<String, Integer>).get(key)
//            String monthYearLabel = "$key $value"
//            finalList.add idx, monthYearLabel
//        }
//        recapService.nomFeuilles().eachWithIndex {
//            String str, int i ->
//                assert finalList.get(i) == str
//        }
//    }
//
//    @Test
//    @Order(2)
//    void testInit() {
//        recapService.path = homeDirectoryName
//        Integer nbMois = interDataService.countMois()
//        String fullClasseurPath = classeurResource.URI.path
//        SpreadsheetRecap expectedClasseur = new SpreadsheetRecap(
//                classeurPathName: fullClasseurPath,
//                nbFeuille: nbMois,
//                nomFeuilles: recapService.nomFeuilles(),
//                moisParAnnee: interDataService.findAllMoisFormatFrParAnnee())
//        SpreadsheetRecap resultRecap = recapService.init()
//        assert expectedClasseur.classeurPathName ==
//                resultRecap.classeurPathName
//        assert expectedClasseur.nbFeuille ==
//                resultRecap.nbFeuille
//        expectedClasseur.nomFeuilles.eachWithIndex {
//            String entry, int i ->
//                assert entry == resultRecap.nomFeuilles.get(i)
//        }
//        expectedClasseur.moisParAnnee.eachWithIndex {
//            Map<String, Integer> item, int idx ->
//                assert Maps.difference(
//                        expectedClasseur.moisParAnnee.get(idx),
//                        resultRecap.moisParAnnee.get(idx))
//                        .areEqual()
//        }
//    }
//
//    @Test
//    @Order(3)
//    void testConstructRecap() {
//        Integer anneeIntValue = 2018
//        Integer moisInt = Month.DECEMBER.value
//        String nomFeuille = "${Month.DECEMBER.getDisplayName(TextStyle.FULL, Locale.FRANCE)} ${anneeIntValue}"
//
//
//        Recap expectedRecap = new Recap(
//                sheetName: nomFeuille,
//                inters: dao.findAllDeMoisDansAnnee(
//                        moisInt, anneeIntValue).collect {
//                    InterventionEntity it ->
//                        new InterventionDto(it)
//                },
//                annee: anneeIntValue,
//                mois: moisInt,
//                nbInterTotal: dao
//                        .countInterParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBaocBaap: dao
//                        .countRacParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBafa: dao
//                        .countBafaParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBast: dao
//                        .countBastParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPlp: dao
//                        .countPlpParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbSav: dao
//                        .countSavParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcTotal: dao
//                        .countPdcParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBafa: dao
//                        .countPdcBafaParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBast: dao
//                        .countPdcBastParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBaocBaap: dao
//                        .countPdcBaocBaapParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                labelTitreRecap:
//                        "${Recap.PRE_LABEL_TITRE_RECAP}" +
//                                "${InterUtils.convertNombreEnMois moisInt}" +
//                                " $anneeIntValue",
//                labelCurrentMonthYearFormattedFr:
//                        InterUtils.convertNombreEnMois(moisInt))
//
//        Integer monthIdx = 2
//        recapService.path = homeDirectoryName
//        SpreadsheetRecap classeurResult = recapService.init()
//        Recap resultRecap = recapService.processRecap(
//                classeurResult.nomFeuilles.get(monthIdx),
//                moisInt, anneeIntValue)
//        assert expectedRecap.sheetName == resultRecap.sheetName
//        expectedRecap.inters.eachWithIndex { InterventionDto entry, int i ->
//            assert entry == resultRecap.inters.get(i)
//        }
//        assert expectedRecap.annee == resultRecap.annee
//        assert expectedRecap.mois == resultRecap.mois
//        assert expectedRecap.nbInterTotal == resultRecap.nbInterTotal
//        assert expectedRecap.nbBafa == resultRecap.nbBafa
//        assert expectedRecap.nbBaocBaap == resultRecap.nbBaocBaap
//        assert expectedRecap.nbBast == resultRecap.nbBast
//        assert expectedRecap.nbPlp == resultRecap.nbPlp
//        assert expectedRecap.nbSav == resultRecap.nbSav
//        assert expectedRecap.nbPdcTotal == resultRecap.nbPdcTotal
//        assert expectedRecap.nbPdcBafa == resultRecap.nbPdcBafa
//        assert expectedRecap.nbPdcBast == resultRecap.nbPdcBast
//        assert expectedRecap.nbPdcBaocBaap == resultRecap.nbPdcBaocBaap
//        assert expectedRecap.labelTitreRecap ==
//                resultRecap.labelTitreRecap
//        assert expectedRecap.labelCurrentMonthYearFormattedFr ==
//                resultRecap.labelCurrentMonthYearFormattedFr
//    }
//
//    @Test
//    @Order(4)
//    void testProcessFeuille() {
//        Integer nbMois = interDataService.countMois()
//        SpreadsheetRecap expectedClasseur = new SpreadsheetRecap(
//                classeurPathName: classeurResource.URI.path,
//                nbFeuille: nbMois,
//                nomFeuilles: recapService.nomFeuilles(),
//                moisParAnnee: interDataService.findAllMoisFormatFrParAnnee())
//
//
//        List<List<Integer>> listIntMoisAnnee =
//                dao.distinctMoisParAnnee()
//        expectedClasseur.recaps = new ArrayList<Recap>(expectedClasseur.nbFeuille)
//        assert expectedClasseur.nbFeuille == expectedClasseur.moisParAnnee.size()
//        assert expectedClasseur.nbFeuille == listIntMoisAnnee.size()
//
//        if (expectedClasseur.nbFeuille == null || expectedClasseur.nbFeuille <= 0) {
//            expectedClasseur.recaps = new ArrayList<Recap>()
//            expectedClasseur.nbFeuille = 0
//        } else {
//            expectedClasseur.moisParAnnee.eachWithIndex {
//                Map<String, Integer> map, int idx ->
//                    String moisStrKey = map.keySet().first()
//                    Integer anneeIntValue = map.get(moisStrKey)
//                    Integer moisInt = listIntMoisAnnee.get(idx).first()
//                    expectedClasseur.recaps.add idx,
//                            new Recap(
//                                    sheetName: expectedClasseur.nomFeuilles.get(idx),
//                                    inters: dao.findAllDeMoisDansAnnee(
//                                            moisInt, anneeIntValue)
//                                            .collect {
//                                                InterventionEntity it ->
//                                                    new InterventionDto(it)
//                                            },
//                                    annee: anneeIntValue,
//                                    mois: moisInt,
//                                    nbInterTotal: dao
//                                            .countInterParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbBaocBaap: dao
//                                            .countRacParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbBafa: dao
//                                            .countBafaParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbBast: dao
//                                            .countBastParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPlp: dao
//                                            .countPlpParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbSav: dao
//                                            .countSavParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPdcTotal: dao
//                                            .countPdcParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPdcBafa: dao
//                                            .countPdcBafaParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPdcBast: dao
//                                            .countPdcBastParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPdcBaocBaap: dao
//                                            .countPdcBaocBaapParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    labelTitreRecap:
//                                            "${Recap.PRE_LABEL_TITRE_RECAP}" +
//                                                    "${InterUtils.convertNombreEnMois moisInt}" +
//                                                    " $anneeIntValue",
//                                    labelCurrentMonthYearFormattedFr:
//                                            InterUtils.convertNombreEnMois(moisInt))
//            }
//        }
//
//        recapService.path = homeDirectoryName
//        SpreadsheetRecap resultClasseur = recapService.processFeuilles()
//
//        expectedClasseur.nomFeuilles.eachWithIndex { String entry, int i ->
//            assert entry == resultClasseur.nomFeuilles.get(i)
//        }
//        expectedClasseur.moisParAnnee.eachWithIndex {
//            Map<String, Integer> entry, int i ->
//                assert Maps.difference(
//                        entry,
//                        resultClasseur.moisParAnnee.get(i)
//                ).areEqual()
//        }
//        assert expectedClasseur.nbFeuille == resultClasseur.nbFeuille
//        assert expectedClasseur.classeurPathName == resultClasseur.classeurPathName
//    }
//
//    @Test
//    @Order(5)
//    void testProcessClasseurFeuilles() {
//        String separator = System.getProperty('file.separator')
//        File pwd = new File(applicationContext
//                .getResource("/").URI)//pwd
//        String baseFolderPath = pwd.path
//
//        String pathFiberUserDataFolderName = baseFolderPath +
//                separator +
//                homeDirectoryName
//
//
//        File pathFiberUserDataFolderNameDir =
//                new File(pathFiberUserDataFolderName)
//
//        pathFiberUserDataFolderNameDir.exists() &&
//                pathFiberUserDataFolderNameDir.directory ?:
//                pathFiberUserDataFolderNameDir.mkdir()//.fiber
//
//
//        String pathOutput = baseFolderPath +
//                separator +
//                homeDirectoryName +
//                separator +
//                classeurResource.getFile().name
//
//
//        File outputDir = new File(pathOutput)
//        outputDir.exists() &&
//                outputDir.directory ?:
//                outputDir.mkdir()//output
//
//        Resource resource = applicationContext
//                .getResource("file:" +
//                        "$pathOutput$separator" +
//                        "$classeurPathName")
//        !resource.exists() ?: resource.file.delete()
//
//        assert !resource.exists()
//
//        recapService.processClasseurFeuilles(baseFolderPath)
//        assert applicationContext
//                .getResource("file:$pathOutput$separator" +
//                        "$classeurPathName")
//                .file
//                .exists()
//    }
}
