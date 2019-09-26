package com.cheroliv.fiber.inter.repository

import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.InterConstants
import com.cheroliv.fiber.inter.domain.InterUtils
import com.cheroliv.fiber.inter.domain.enumeration.InterContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.InterTypeEnum
import com.cheroliv.fiber.repository.AbstractInterTestCase
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

import java.time.*
import java.time.format.DateTimeFormatter

@Slf4j
@TypeChecked
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestMethodOrder(OrderAnnotation)
@DisplayName('InterRepositoryTest')
class InterRepositoryTest extends AbstractInterTestCase {

    @BeforeEach
    void setUp() {
        super.setUp()
    }

    @Test
    @Order(1)
    @DisplayName('testFind_withNdAndType')
    void testFind_withNdAndType() {
        Map<String, String> expectedMap = jsonData.first()
        Inter expectedInter = jsonDataToInter(expectedMap)
        println "\n\n\n\n$expectedMap"
        println expectedInter
        //because jsonData.first() then id must be 1 and
        // not expectedInter.id cant trust the id in json file
        // only can what the DB will generate
        Optional<Inter> optionalResultInter = interRepository
                .findById 1L//not expectedInter.id
        println optionalResultInter.get()
        if (optionalResultInter.present) {
            Inter resultInter = optionalResultInter.get()
            Optional<Inter> optionalInterResult = interRepository.find(
                    resultInter.nd, resultInter.typeInter)
            if (optionalInterResult.present) {
                Inter result = optionalInterResult.get()
                assert expectedInter.equals(result)
            } else throw new NoSuchElementException(
                    "No value present for find(${resultInter.nd}," +
                            " ${resultInter.typeInter.name()})")
        } else throw new NoSuchElementException(
                "No value present for findById(${expectedInter.id})")
    }


    @Test
    @Order(2)
    @DisplayName('testFindAllDeMoisDansAnnee_withMoisAndAnnee')
    void testFindAllDeMoisDansAnnee_withMoisAndAnnee() {
        Integer intAnnee = 2018
        Integer intMois = Month.OCTOBER.value
        List<Inter> expectedResult = new ArrayList<>()
        jsonData.each {
            LocalDate date = LocalDate.parse(
                    it[InterConstants.DATE_INTER_JSON_FIELD_NAME],
                    DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            if (date.year == intAnnee &&
                    date.monthValue == intMois)
                expectedResult.add(jsonDataToInter(it))
        }
        List<Inter> result = interRepository
                .findAllDeMoisDansAnnee intMois, intAnnee
        assert expectedResult.size() == result.size()
        expectedResult.eachWithIndex {
            Inter entry, int i ->
                assert entry.equals(result.get(i))
        }
    }


    @Test
    @Order(3)
    @DisplayName('testDistinctMoisParAnnee')
    void testDistinctMoisParAnnee() {
        List<List<Integer>> expectedResult = this.getAnneesMoisDistinct()
        interRepository.distinctMoisParAnnee().eachWithIndex {
            List<Integer> list, int x ->
                list.eachWithIndex {
                    int integer, int y ->
                        assert expectedResult.get(x).get(y) == integer
                }
        }
    }


    @Test
    @Order(4)
    void testCountPlpParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = this.getJsonData()
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(
                        map[InterConstants.DATE_INTER_JSON_FIELD_NAME])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it[InterConstants
                    .DATE_INTER_JSON_FIELD_NAME]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it[InterConstants.TYPE_INTER_JSON_FIELD_NAME] ==
                    InterTypeEnum.PLP.name()) {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countPlpParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(5)
    void testCountRacParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    (it["type"] == "BAAP" ||
                            it["type"] == "BAOC" ||
                            it["type"] == "BAFA" ||
                            it["type"] == "BAST") &&
                    it["contrat"] != "Passage de cable") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countRacParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(6)
    void testCountInterParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue) {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countInterParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(7)
    void testCountBafaParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["type"] == "BAFA" &&
                    it["contrat"] != "Passage de cable") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countBafaParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(8)
    void testCountBastParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["type"] == "BAST" &&
                    it["contrat"] != "Passage de cable") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countBastParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }


    @Test
    @Order(9)
    void testCountSavParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> jsonMap = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(jsonMap["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["type"] == "SAV") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countSavParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(10)
    void testCountPdcParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["contrat"] == "Passage de cable") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countPdcParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(11)
    void testCountPdcBaapParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["contrat"] == "Passage de cable" &&
                    it["type"] == "BAAP") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countPdcBaapParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(12)
    void testCountPdcBaocParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["contrat"] == "Passage de cable" &&
                    it["type"] == "BAOC") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countPdcBaocParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(13)
    void testCountPdcBafaParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["contrat"] == "Passage de cable" &&
                    it["type"] == "BAFA") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countPdcBafaParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(14)
    void testCountPdcBastParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["contrat"] == "Passage de cable" &&
                    it["type"] == "BAST") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countPdcBastParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(15)
    void testCountBaocParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["type"] == "BAOC" &&
                    it["contrat"] != "Passage de cable") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countBaocParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(16)
    void testCountBaapParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["type"] == "BAAP" &&
                    it["contrat"] != "Passage de cable") {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countBaapParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }

    @Test
    @Order(17)
    void testCountPdcBaocBaapParMoisDansAnnee_withMoisAndAnnee() {
        List<Map<String, String>> data = jsonData
        Map<String, String> map = data.get(3)
        LocalDate dateExpected = InterUtils
                .parseStringDateToLocalDate(map["date"])
        Integer expectedCount = 0
        data.each {
            LocalDate date = InterUtils
                    .parseStringDateToLocalDate it["date"]
            if (date.year == dateExpected.year &&
                    date.monthValue == dateExpected.monthValue &&
                    it["contrat"] == "Passage de cable" &&
                    (it["type"] == "BAOC" ||
                            it["type"] == "BAAP")
            ) {
                expectedCount++
            }
        }
        assert expectedCount ==
                interRepository.countPdcBaocBaapParMoisDansAnnee(
                        dateExpected.monthValue,
                        dateExpected.year)
    }


    //pas utile de test le frmwrk Spring data
    //juste pour garder un exemple de rollback en test
    @Test
    @Order(18)
    @Rollback
    @Transactional
    void testSave() {
        Long countBefore = interRepository.count()
        interRepository.save new Inter(
                nd: "0101010101",
                lastNameClient: "Doe",
                firstNameClient: "John",
                typeInter: InterTypeEnum.valueOfName("BAFA"),
                contract: InterContractEnum.valueOfName("LM"),
                dateTimeInter: ZonedDateTime.of(
                        LocalDate.now(),
                        LocalTime.now(),
                        ZoneId.systemDefault()))
        Long countAfter = interRepository.count()
        assert countAfter == countBefore + 1
    }
}
