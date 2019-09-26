package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.InterUtils
import com.cheroliv.fiber.AbstractInterTestCase
import com.google.common.collect.Maps
import groovy.json.JsonSlurper
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.apache.commons.io.FilenameUtils
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.domain.Sort
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

import java.text.SimpleDateFormat

@Slf4j
@TypeChecked
@Transactional(readOnly = true)
@TestMethodOrder(OrderAnnotation)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class InterDataServiceImplTest extends AbstractInterTestCase {


    @Autowired
    InterDataService interDataService

    @BeforeEach
    void setUp() {
        super.setUp()
    }

    @Test
    @Order(1)
    @DisplayName('testFind_withNdAndType')
    void testFind_withNdAndType() {
        Map<String, String> expectedMap = this.getJsonData().first()
        Inter expectedInter = InterUtils.mapToInter(expectedMap)
        assert expectedInter.equals(
                interDataService.find(expectedInter.nd,
                        expectedInter.typeInter.name()))
    }

    @Test
    @Order(2)
    @DisplayName('testCountMois')
    void testCountMois() {
        assert this.getAnneesMoisDistinct()
                .size() == interDataService.countMois()
    }


    @Test
    @Order(3)
    @DisplayName('testFindAllMoisFormatFrParAnnee')
    void testFindAllMoisFormatFrParAnnee() {
        List<Map<String, Integer>> expectedResult = this.getMoisFormatFrParAnnee()
        println expectedResult.toListString()
        println interDataService.findAllMoisFormatFrParAnnee().toListString()
        interDataService.findAllMoisFormatFrParAnnee()
                .eachWithIndex { Map<String, Integer> entry, int i ->
                    assert Maps.difference(
                            expectedResult.get(i), entry)
                            .areEqual()
//same test first with framework second without
                    assert entry.keySet().first() ==
                            expectedResult.get(i).keySet().first()
                    assert entry.get(entry.keySet().first()) ==
                            expectedResult.get(i)
                                    .get(expectedResult.get(i)
                                            .keySet().first())
                }

    }

    @Test
    @Order(4)
    void testGetFiberJsonFilePath_path() {
        String expectedPath = System.getProperty('user.home') +
                System.getProperty('file.separator') +
                this.homeDirectoryName +
                System.getProperty('file.separator') +
                this.jsonBackupFileName

        assert expectedPath ==
                interDataService.getJsonBackupFilePath()
    }


    @Test
    @Order(5)
    @Rollback
    @Transactional
    @DisplayName('testImportJsonFromFile')
    void testImportJsonFromFile() {
        truncateDB()//vider la base avant le test
        assert 0 == interRepository.count().toInteger()
//        String path = jsonDatasetPath
        assert resourceFile.exists()
        Object jsonInters = new JsonSlurper().parse resourceFile.getFile()
        interDataService.importJsonFromFile()
        assert interRepository.count() == (jsonInters as List<Map<String, String>>).size()
        //test toutes les données sont conformes
        List<Map<String, String>> expectedData = this.getJsonData()
        interRepository.findAll(
                Sort.by("id")
                        .ascending())
                .eachWithIndex { inter, i ->
                    assert (InterUtils
                            .mapToInter(expectedData
                                    .get(i))
                            .equals(inter))
                }
    }

    @Test
    @Order(6)
    void testBuildJsonInter_withInter() {
        String expectedJson = "{\"id_inter\":\"1\", " +
                "\"ND\":\"0144639035\", \"nom\":\"Lalande\"," +
                " \"prenom\":\"Julien\", \"heure\":\"10:00:00\"," +
                " \"date\":\"2018-10-29\", \"contrat\":\"IQ\"," +
                " \"type\":\"BAOC\"}"
        String resultJson = interDataService
                .buildJsonInter(interRepository
                        .findById(1L).get())
        JSONAssert.assertEquals(
                expectedJson,
                resultJson,
                true)
    }

    @Test
    @Order(7)
    @DisplayName('testSaveToJsonFile')
    void testSaveToJsonFile() {
        File jsonDataFile = this.resourceFile.getFile()

        assert jsonDataFile.exists()
        assert !jsonDataFile.directory

        interDataService.saveToJsonFile()

        String ajout = new SimpleDateFormat("yyyyMMddHHmmss")
                .format new Date()
        String jsonBackUpFilename =
                "${FilenameUtils.removeExtension(this.getJsonBackupFilePath())}" +
                        "${ajout}.json"
        File jsonBackUpFile = new File("${jsonBackUpFilename}")

        assert jsonBackUpFile.exists()
        assert !jsonBackUpFile.directory
    }
}
