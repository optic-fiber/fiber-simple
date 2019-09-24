package com.cheroliv.fiber.service

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@Slf4j
@SpringBootTest
@TestMethodOrder(OrderAnnotation)
@DisplayName("SettingServiceImplTest")
class SettingServiceImplTest {
/*
        log.info(
                """///////////////////////////\n
expectedDataHomeDirectoryFile.exists() : ${expectedDataHomeDirectoryFile.exists()}
expectedDataHomeDirectoryFile.directory : ${expectedDataHomeDirectoryFile.directory}
expectedDataHomeDirectoryFile.file : ${expectedDataHomeDirectoryFile.file}


expectedDataHomeDirectoryPath : ${expectedDataHomeDirectoryPath}
\n////////////////////""")
 */


    @Autowired
    SettingService settingService
    @Value('${application.data.home-directory-name}')
    String homeDirectoryName
    @Value('${application.data.json-backup-file-name}')
    String jsonBackupFileName

    String getExpectedDataHomeDirectoryPath() {
        System.getProperty('user.home') +
                System.getProperty('file.separator') +
                homeDirectoryName
    }

    private void deleteFileCreateDirectory() {
        File file = new File(this.getExpectedDataHomeDirectoryPath())
        file.delete()
        file.mkdir()
    }

    private void createTestHomeDirectory() {
        log.info("${this.class.simpleName}.createTestHomeDirectory()")
        File expectedDataHomeDirectoryFile = new File(this.getExpectedDataHomeDirectoryPath())
        expectedDataHomeDirectoryFile.exists() ?
                (expectedDataHomeDirectoryFile.directory &&
                        !expectedDataHomeDirectoryFile.file ?:
                        this.deleteFileCreateDirectory()) :
                expectedDataHomeDirectoryFile.mkdir()
        assert expectedDataHomeDirectoryFile.exists() &&
                expectedDataHomeDirectoryFile.directory &&
                !expectedDataHomeDirectoryFile.file
    }


    @Test
    @Order(1)
    @DisplayName("testIsAppDataHomeDirExists_exists_isDirectory")
    void testIsAppDataHomeDirExists_exists_isDirectory() {
        this.createTestHomeDirectory()
        File expectedDataHomeDirectoryFile =
                new File(this.getExpectedDataHomeDirectoryPath())
        assert expectedDataHomeDirectoryFile.exists()
        assert expectedDataHomeDirectoryFile.directory
        assert !expectedDataHomeDirectoryFile.file
        assert this.settingService.isAppDataHomeDirectoryExists()
    }

    private void deleteTestHomeDirectory() {
        log.info("${this.class.simpleName}.deleteTestHomeDirectory()")
        File file = new File(this.getExpectedDataHomeDirectoryPath())
        if (file.exists()) {
            if (file.directory) file.deleteDir()
        }
        assert !(file.directory && file.exists())
    }

    private void deleteTestHomeDirectoryAsFile() {
        log.info("${this.class.simpleName}.deleteTestHomeDirectoryAsFile()")
        File file = new File(this.getExpectedDataHomeDirectoryPath())
        if (file.exists()) {
            if (file.file) file.delete()
        }
        assert !file.exists()
    }

    private void deleteTestHomeDirectoryAndFiles() {
        log.info("${this.class.simpleName}.deleteTestHomeDirectoryAndFiles()")
        this.deleteTestHomeDirectory()
        this.deleteTestHomeDirectoryAsFile()
        assert !new File(this.getExpectedDataHomeDirectoryPath()).exists()
    }


    @Test
    @Order(2)
    @DisplayName("testIsAppDataHomeDirExists_directory_not_exists")
    void testIsAppDataHomeDirExists_directory_not_exists() {
        this.deleteTestHomeDirectory()
        def file = new File(this.getExpectedDataHomeDirectoryPath())
        assert !(file.exists() && file.directory)
        assert !this.settingService.isAppDataHomeDirectoryExists()
        log.info("${this.class.simpleName}.testIsAppDataHomeDirExists_directory_not_exists() FIN")
    }

    private void createTestHomeDirectoryAsFile() {
        log.info("${this.class.simpleName}.createTestHomeDirectoryAsFile()")
        assert new File(this.getExpectedDataHomeDirectoryPath()).createNewFile()
        //TODO: s'assurer que le fichier .fiber-simple-test est créé
        log.info("${this.class.simpleName}.createTestHomeDirectoryAsFile() FIN")
    }

    @Test
    @Order(3)
    @DisplayName("testIsAppDataHomeDirExists_is_file")
    void testIsAppDataHomeDirExists_is_file() {
        this.deleteTestHomeDirectoryAndFiles()
        this.createTestHomeDirectoryAsFile()
//        File file = new File(this.getExpectedDataHomeDirectoryPath())
//        assert file.exists()
//        assert file.file
//        assert !file.directory
//        assert !this.settingService.isAppDataHomeDirectoryExists()
    }


//    @Test
//    @Order(4)
//    @DisplayName("createAppDataHomeDirectory")
//    void createAppDataHomeDir() {
//    }

//    @Test
//    @Order(5)
//    @DisplayName("settingUpApp")
//    void settingUpApp() {
//    }
}