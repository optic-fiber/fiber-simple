package com.cheroliv.fiber.console.service

//import com.cheroliv.com.cheroliv.fiber.calc.ClasseurRecap
//import com.cheroliv.com.cheroliv.fiber.calc.Recap
//import com.cheroliv.fiber.console.dao.AbstractInterTestCase
//import com.cheroliv.fiber.domain.Inter
//import com.cheroliv.fiber.domain.InterUtils
//import com.google.common.collect.Maps
//import groovy.transform.CompileStatic
//import groovy.util.logging.Slf4j
//import org.junit.jupiter.api.*
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.core.io.Resource
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.junit.jupiter.SpringExtension
//
//import java.time.Month
//import java.time.format.TextStyle
//
//@Slf4j
//@CompileStatic
//@SpringBootTest
//@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation)
class RecapServiceImplTest
//        extends AbstractInterTestCase
{
//    @Autowired
//    RecapService recapService
//    @Autowired
//    InterService interService
//
//
//    @BeforeEach
//    void setUp() {
//        super.setUp()
//    }
//
//    @Test
//    @Order(1)
//    void testNomFeuilles() {
//        Integer countMois = interService.countMois()
//        List<Map<String, Integer>> list = interService.findAllMoisFormatFrParAnnee()
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
//        recapService.path = rootPathData
//        Integer nbMois = interService.countMois()
//        String fullClasseurPath = classeurResource.URI.path
//        ClasseurRecap expectedClasseur = new ClasseurRecap(
//                classeurPathName: fullClasseurPath,
//                nbFeuille: nbMois,
//                nomFeuilles: recapService.nomFeuilles(),
//                moisParAnnee: interService.findAllMoisFormatFrParAnnee())
//        ClasseurRecap resultRecap = recapService.init()
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
//                inters: interRepository
//                        .findAllDeMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                annee: anneeIntValue,
//                mois: moisInt,
//                nbInterTotal: interRepository
//                        .countInterParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBaocBaap: interRepository
//                        .countRacParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBafa: interRepository
//                        .countBafaParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBast: interRepository
//                        .countBastParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPlp: interRepository
//                        .countPlpParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbSav: interRepository
//                        .countSavParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcTotal: interRepository
//                        .countPdcParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBafa: interRepository
//                        .countPdcBafaParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBast: interRepository
//                        .countPdcBastParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBaocBaap: interRepository
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
//        recapService.path = rootPathData
//        ClasseurRecap classeurResult = recapService.init()
//        Recap resultRecap = recapService.processRecap(
//                classeurResult.nomFeuilles.get(monthIdx),
//                moisInt, anneeIntValue)
//        assert expectedRecap.sheetName == resultRecap.sheetName
//        expectedRecap.inters.eachWithIndex { Inter entry, int i ->
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
//        Integer nbMois = interService.countMois()
//        ClasseurRecap expectedClasseur = new ClasseurRecap(
//                classeurPathName: classeurResource.URI.path,
//                nbFeuille: nbMois,
//                nomFeuilles: recapService.nomFeuilles(),
//                moisParAnnee: interService.findAllMoisFormatFrParAnnee())
//
//
//        List<List<Integer>> listIntMoisAnnee =
//                interRepository.distinctMoisParAnnee()
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
//                                    inters: interRepository
//                                            .findAllDeMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    annee: anneeIntValue,
//                                    mois: moisInt,
//                                    nbInterTotal: interRepository
//                                            .countInterParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbBaocBaap: interRepository
//                                            .countRacParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbBafa: interRepository
//                                            .countBafaParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbBast: interRepository
//                                            .countBastParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPlp: interRepository
//                                            .countPlpParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbSav: interRepository
//                                            .countSavParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPdcTotal: interRepository
//                                            .countPdcParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPdcBafa: interRepository
//                                            .countPdcBafaParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPdcBast: interRepository
//                                            .countPdcBastParMoisDansAnnee(
//                                                    moisInt, anneeIntValue),
//                                    nbPdcBaocBaap: interRepository
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
//        recapService.path = rootPathData
//        ClasseurRecap resultClasseur = recapService.processFeuilles()
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
//        File pwd = new File(applicationContext
//                .getResource("/").URI)//pwd
//        String baseFolderPath = pwd.path
//
//        String pathFiberUserDataFolderName = baseFolderPath +
//                separator +
//                fiberUserDataFolderName
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
//                fiberUserDataFolderName +
//                separator +
//                classeurDirectoryName
//
//
//        File outputDir = new File(pathOutput)
//        outputDir.exists() &&
//                outputDir.directory ?:
//                outputDir.mkdir()//output
//
//        Resource resource = applicationContext
//                .getResource("file:$pathOutput$separator$classeurPathName")
//        !resource.exists() ?: resource.file.delete()
//
//        assert !resource.exists()
//
//        recapService.processClasseurFeuilles(baseFolderPath)
//        assert applicationContext
//                .getResource("file:$pathOutput$separator$classeurPathName")
//                .file
//                .exists()
//    }
}
