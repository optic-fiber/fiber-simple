package com.cheroliv.fiber.config

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.io.Resource

@Slf4j
@SpringBootTest
@DisplayName('ApplicationPropertiesTest')
class ApplicationPropertiesTest implements ApplicationContextAware {

    ApplicationContext applicationContext
    @Value('${application.data.spreadsheet-file-name}')
    String spreadsheetFileName
    @Value('${application.data.home-directory-name}')
    String homeDirectoryName
    @Value('${application.data.json-backup-file-name}')
    String jsonBackupFileName

    Properties getApplicationProperties() {
        Resource propFileRsrc = applicationContext
                .getResource('classpath:application.properties')
        assert propFileRsrc.file, 'la resource demandé n\'est pas un fichier'
        File propFile = propFileRsrc.getFile()
        Properties appProperties = new Properties()
        propFile.withInputStream { InputStream it ->
            appProperties.load(it)
        }
        appProperties
    }

    static boolean verifyProperty(Properties propertiesFromFile,
                                  String injectedString,
                                  String key) {
        propertiesFromFile.containsValue(injectedString) &&
                propertiesFromFile.containsKey(key) &&
                propertiesFromFile[key] == injectedString
    }

    @Test
    @DisplayName('applicationPropertiesLoads()')
    void applicationPropertiesLoads() {
        log.info("""
            jsonBackupFileName : $jsonBackupFileName
            spreadsheetFileName : $spreadsheetFileName
            homeDirectoryName : $homeDirectoryName""")

        Properties propertiesFromFile = this.getApplicationProperties()
        assert verifyProperty(
                propertiesFromFile,
                this.spreadsheetFileName,
                'application.data.spreadsheet-file-name')
        assert verifyProperty(
                propertiesFromFile,
                this.homeDirectoryName,
                'application.data.home-directory-name')
        assert verifyProperty(
                propertiesFromFile,
                this.jsonBackupFileName,
                'application.data.json-backup-file-name')

    }


}