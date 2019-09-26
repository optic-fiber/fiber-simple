package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.InterUtils
import com.cheroliv.fiber.repository.AbstractInterTestCase
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

//import com.google.common.collect.Maps
//import groovy.json.JsonSlurper
//import org.apache.commons.io.FilenameUtils
//import org.junit.jupiter.api.extension.ExtendWith
//import org.skyscreamer.jsonassert.JSONAssert
//import org.springframework.data.domain.Sort
//import org.springframework.test.annotation.Rollback
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.junit.jupiter.SpringExtension
//import org.springframework.transaction.annotation.Transactional
//
//import java.nio.file.Path
//import java.nio.file.Paths
//import java.text.SimpleDateFormat

@Slf4j
@TypeChecked
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@TestMethodOrder(OrderAnnotation)
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
        Inter expectedInter = InterUtils.mapToInstance(expectedMap)
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
//
//
//    @Test
//    @Order(3)
//    void testFindAllMoisFormatFrParAnnee() {
//        List<Map<String, Integer>> expectedResult = moisFormatFrParAnnee
//        interDataService.findAllMoisFormatFrParAnnee()
//                .eachWithIndex { Map<String, Integer> entry, int i ->
//                    assert Maps.difference(
//                            expectedResult.get(i), entry)
//                            .areEqual()
//                }
//
//    }
//
//    @Test
//    @Order(4)
//    void testGetFiberJsonFilePath_path() {
//        String baseFolderPath = "foo"
//        String expectedPath = Paths.get("${baseFolderPath}" +
//                "$separator$fiberUserDataFolderName" +
//                "$separator" +
//                "$jsonBackUpFileName").toString()
//
//        assert expectedPath ==
//                interDataService.getJsonBackupFilePath(baseFolderPath)
//    }
//
//    @Test
//    @Order(4)
//    void testSetUp() {
//        Path path = Paths.get "${System.getProperty("user.home")}$separator" +
//                "$fiberUserDataFolderName"
//        interDataService.setUp()
//        assert path.toFile().exists()
//        assert path.toFile().directory
//    }
//
//    @Test
//    @Order(5)
//    @Transactional
//    @Rollback
//    void testImportJsonFromFile_withPath() {
//        truncateDB()//vider la base avant le test
//        assert 0 == interRepository.count().toInteger()
//        String path = jsonDatasetPath
//        assert new File(path).exists()
//        Object jsonInters = new JsonSlurper().parse new File(path)
//        interDataService.importJsonFromFile(path)
//        assert interRepository.count() == (jsonInters as List<Map<String, String>>).size()
//        //test toutes les données sont conformes
//        List<Map<String, String>> expectedData = jsonData
//        interRepository.findAll(
//                Sort.by("id")
//                        .ascending())
//                .eachWithIndex { inter, i ->
//                    assert (InterUtils
//                            .mapToInstance(expectedData
//                                    .get(i))
//                            .equals(inter))
//                }
//    }
//
//    @Test
//    @Order(6)
//    void testBuildJsonInter_withInter() {
//        String expectedJson = "{\"id_inter\":\"1\", \"ND\":\"0144639035\", \"nom\":\"Lalande\", \"prenom\":\"Julien\", \"heure\":\"10:00:00\", \"date\":\"2018-10-29\", \"contrat\":\"IQ\", \"type\":\"BAOC\"}"
//        String resultJson = interDataService.buildJsonInter(interRepository.findById(1L).get())
//        JSONAssert.assertEquals(expectedJson, resultJson, Boolean.TRUE)
//    }
//
//    @Test
//    @Order(7)
//    void testSaveToJsonFile_withPath() {
//        String path = jsonDatasetPath
//        File jsonDataFile = new File(path)
//
//        assert jsonDataFile.exists()
//        assert !jsonDataFile.directory
//
//        interDataService.saveToJsonFile(jsonDatasetPath)
//
//        String ajout = new SimpleDateFormat("yyyyMMddHHmmss").format new Date()
//        String jsonBackUpFilename = "${FilenameUtils.removeExtension path}${ajout}.json"
//        File jsonBackUpFile = new File("${jsonBackUpFilename}")
//
//        assert jsonBackUpFile.exists()
//        assert !jsonBackUpFile.directory
//    }
}
