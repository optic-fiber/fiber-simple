package com.cheroliv.fiber.console.dao

//import com.cheroliv.fiber.inter.domain.Inter
//import com.cheroliv.fiber.inter.domain.InterUtils
//import groovy.transform.CompileStatic
//import groovy.util.logging.Slf4j
//import org.junit.jupiter.api.*
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.test.annotation.Rollback
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import org.springframework.transaction.annotation.Transactional
//
//import java.time.LocalDate
//import java.time.LocalTime
//import java.time.Month
//
//@Slf4j
//@CompileStatic
//@SpringBootTest
//@ActiveProfiles("test")
//@TestMethodOrder(MethodOrderer.OrderAnnotation)
class InterRepositoryTest
//        extends AbstractInterTestCase
{

//    @BeforeEach
//    void setUp() {
//        super.setUp()
//    }
//
//    @Test
//    @Order(1)
//    void testFind_withNdAndType() {
//        Map<String, String> expectedMap = jsonData.first()
//        Inter expectedInter = InterUtils.mapToInstance(expectedMap)
//        Optional<Inter> optionalResultInter = interRepository
//                .findById expectedInter.id
//        if (optionalResultInter.present) {
//            Inter resultInter = optionalResultInter.get()
//            String nd = resultInter.nd
//            String type = resultInter.type
//            Optional<Inter> optionalInterResult = interRepository.find(nd, type)
//            if (optionalInterResult.present) {
//                Inter result = optionalInterResult.get()
//                assert expectedInter.id == result.id
//                assert expectedInter.equals(result)
//            } else throw new NoSuchElementException("No value present for find($nd, $type)")
//        } else throw new NoSuchElementException("No value present for findById(${expectedInter.id})")
//    }
//
//    @Test
//    @Order(2)
//    void testDistinctMoisParAnnee() {
//        List<List<Integer>> expectedResult = anneesMois
//        interRepository.distinctMoisParAnnee().eachWithIndex {
//            List<Integer> list, int x ->
//                list.eachWithIndex {
//                    int integer, int y ->
//                        assert expectedResult.get(x).get(y) == integer
//                }
//        }
//    }
//
//    @Test
//    @Order(3)
//    void testFindAllDeMoisDansAnnee_withMoisAndAnnee() {
//        Integer intAnnee = 2018
//        Integer intMois = Month.OCTOBER.value
//        List<Inter> expectedResult = new ArrayList<>()
//        jsonData.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == intAnnee &&
//                    date.monthValue == intMois) {
//                expectedResult.add(InterUtils.mapToInstance(it))
//            }
//        }
//        List<Inter> result = interRepository
//                .findAllDeMoisDansAnnee intMois, intAnnee
//        assert expectedResult.size() == result.size()
//        expectedResult.eachWithIndex { Inter entry, int i ->
//            assert entry.equals(result.get(i))
//        }
//    }
//
//    @Test
//    @Order(4)
//    void testCountPlpParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["type"] == "PLP") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countPlpParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(5)
//    void testCountRacParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    (it["type"] == "BAAP" ||
//                            it["type"] == "BAOC" ||
//                            it["type"] == "BAFA" ||
//                            it["type"] == "BAST") &&
//                    it["contrat"] != "Passage de cable") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countRacParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(6)
//    void testCountInterParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue) {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countInterParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(7)
//    void testCountBafaParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["type"] == "BAFA" &&
//                    it["contrat"] != "Passage de cable") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countBafaParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(8)
//    void testCountBastParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["type"] == "BAST" &&
//                    it["contrat"] != "Passage de cable") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countBastParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//
//    @Test
//    @Order(9)
//    void testCountSavParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> jsonMap = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(jsonMap["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["type"] == "SAV") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countSavParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(10)
//    void testCountPdcParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["contrat"] == "Passage de cable") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countPdcParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(11)
//    void testCountPdcBaapParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["contrat"] == "Passage de cable" &&
//                    it["type"] == "BAAP") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countPdcBaapParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(12)
//    void testCountPdcBaocParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["contrat"] == "Passage de cable" &&
//                    it["type"] == "BAOC") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countPdcBaocParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(13)
//    void testCountPdcBafaParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["contrat"] == "Passage de cable" &&
//                    it["type"] == "BAFA") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countPdcBafaParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(14)
//    void testCountPdcBastParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["contrat"] == "Passage de cable" &&
//                    it["type"] == "BAST") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countPdcBastParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(15)
//    void testCountBaocParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["type"] == "BAOC" &&
//                    it["contrat"] != "Passage de cable") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countBaocParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(16)
//    void testCountBaapParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["type"] == "BAAP" &&
//                    it["contrat"] != "Passage de cable") {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countBaapParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//    @Test
//    @Order(17)
//    void testCountPdcBaocBaapParMoisDansAnnee_withMoisAndAnnee() {
//        List<Map<String, String>> data = jsonData
//        Map<String, String> map = data.get(3)
//        LocalDate dateExpected = InterUtils
//                .parseStringDateToLocalDate(map["date"])
//        Integer expectedCount = 0
//        data.each {
//            LocalDate date = InterUtils
//                    .parseStringDateToLocalDate it["date"]
//            if (date.year == dateExpected.year &&
//                    date.monthValue == dateExpected.monthValue &&
//                    it["contrat"] == "Passage de cable" &&
//                    (it["type"] == "BAOC" ||
//                            it["type"] == "BAAP")
//            ) {
//                expectedCount++
//            }
//        }
//        assert expectedCount ==
//                interRepository.countPdcBaocBaapParMoisDansAnnee(
//                        dateExpected.monthValue,
//                        dateExpected.year)
//    }
//
//
//    //pas utile de test le frmwrk Spring data
//    //juste pour garder un exemple de rollback en test
//    @Test
//    @Order(18)
//    @Transactional
//    @Rollback
//    void testSave() {
//        Long countBefore = interRepository.count()
//        interRepository.save new Inter(
//                nd: "0101010101",
//                nom: "Doe",
//                prenom: "John",
//                type: "BAFA",
//                contrat: "LM",
//                heure: LocalTime.now(),
//                date: LocalDate.now())
//        Long countAfter = interRepository.count()
//        assert countAfter == countBefore + 1
//    }
//
//    //pas utile juste pour le plaisir
//    @Test
//    @Order(19)
//    void testFindAllInter() {
//        List<Map<String, String>> expectedData = jsonData
//        List<Inter> result = interRepository.findAll()
//
//        assert expectedData.size() == result.size()
//
//        result.eachWithIndex { inter, i ->
//            assert Long.parseLong(
//                    expectedData.get(i)["id_inter"]) ==
//                    inter.id
//            assert InterUtils
//                    .mapToInstance(expectedData
//                            .get(i))
//                    .equals(inter)
//        }
//    }

}
