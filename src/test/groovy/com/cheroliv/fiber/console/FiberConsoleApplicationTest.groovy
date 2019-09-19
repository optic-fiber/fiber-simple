package com.cheroliv.fiber.console

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.io.Resource
import org.springframework.test.context.ActiveProfiles

@Slf4j
@SpringBootTest
@ActiveProfiles("test")
class FiberConsoleApplicationTest implements ApplicationContextAware {

    ApplicationContext applicationContext
    @Value('${application.classeur.directory-name}')
    String classeurdirName
    @Value('${application.classeur.path-name}')
    String pathName
    @Value('${application.data.directory-name}')
    String dataDirName
    @Value('${application.data.json-backup-file-name}')
    String jsonBackupFile


    @Test
    void contextLoads() {
        assert applicationContext.getBeanDefinitionCount() > 0
    }

    @Test
    void applicationPropertiesLoads() {
        Properties propertiesFromFile = applicationProperties
        assert verifyProperty(
                propertiesFromFile,
                this.classeurdirName,
                'application.classeur.directory-name')
        assert verifyProperty(
                propertiesFromFile,
                this.pathName,
                'application.classeur.path-name')

        assert verifyProperty(
                propertiesFromFile,
                this.dataDirName,
                'application.data.directory-name')
        assert verifyProperty(
                propertiesFromFile,
                this.jsonBackupFile,
                'application.data.json-backup-file-name')
    }

    Properties getApplicationProperties() {
        Resource propFileRsrc = applicationContext
                .getResource("classpath:application.properties")
        assert propFileRsrc.file, "la resource demandé n'est pas un fichier"
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


}