package com.cheroliv.fiber.recap.service


import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE

@Slf4j
@TypeChecked
@TestMethodOrder(OrderAnnotation)
@SpringBootTest(webEnvironment = NONE)
class RecapSettingServiceIntegrationTest {
    @Autowired
    RecapSettingService settingService
    @Value('${application.data.home-directory-name}')
    String homeDirectoryName
    //=spreadsheet
//    @Value('application.data.recapitulatif-spreadsheet-directory-name')
//    String recapitulatifSpreadsheetDirectoryName
    //=recap_date-time1_date-time2.xlsx
//    @Value('application.data.recapitulatif-spreadsheet-file-name')
//    String recapitulatifSpreadsheetFileName

    String getHomeDataDirectoryPath() {
        new File(System.getProperty('user.home') +
                System.getProperty('file.separator') +
                homeDirectoryName).path
    }

    void deleteHomeDataDirectory() {
        File file = new File(getHomeDataDirectoryPath())
        if (file.exists()) {
            if (file.isDirectory()) file.deleteDir()
            if (file.isFile()) file.delete()
        }
    }

    void createHomeDataDirectory() {
        File file = new File(getHomeDataDirectoryPath())
        if (!file.exists()) file.createNewFile()
        else if (file.isFile()) {
            file.delete()
            file.mkdir()
        }
    }

    @Test
    @Order(1)
    @DisplayName('testCheckDataHomeDirectoryExists_not_exists')
    void testCheckDataHomeDirectoryExists_not_exists() {
        deleteHomeDataDirectory()
        File file = new File(getHomeDataDirectoryPath())
        assert !file.exists()
        assert !settingService.checkDataHomeDirectoryExists()
    }


    @Test
    @Order(2)
    @DisplayName('testCheckDataHomeDirectoryExists_exists')
    void testCheckDataHomeDirectoryExists_exists() {
        createHomeDataDirectory()
        File file = new File(getHomeDataDirectoryPath())
        assert file.exists()
        assert file.isDirectory()
        assert settingService.checkDataHomeDirectoryExists()

    }

}
