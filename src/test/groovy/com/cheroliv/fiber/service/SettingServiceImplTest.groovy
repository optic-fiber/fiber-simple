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
    @DisplayName("testIsAppDataHomeDirExists_dir_exists_isDirectory")
    void testIsAppDataHomeDirExists_dir_exists_isDirectory() {
        log.info(
                """///////////////////////////\n
expectedDataHomeDirectoryPath : ${expectedDataHomeDirectoryPath}
\n////////////////////""")
        this.createTestHomeDirectory()
        File expectedDataHomeDirectoryFile = new File(expectedDataHomeDirectoryPath)
        if (expectedDataHomeDirectoryFile.exists() &&
                expectedDataHomeDirectoryFile.directory) {
            assert !this.settingService.isAppDataHomeDirectoryExists()
        }
    }


    private void deleteTestHomeDirectory() {
        log.info("${this.class.simpleName}.deleteTestHomeDirectory()")
        File homeDirTest = new File(System.getProperty('user.home')
                + this.homeDirectoryName)
    }

    private void createTestHomeDirectoryAsFile() {
        log.info("${this.class.simpleName}.createTestHomeDirectoryAsFile()")
    }

    @Test
    @Order(2)
    @DisplayName("testIsAppDataHomeDirExists_dir_not_exists")
    void testIsAppDataHomeDirExists_dir_not_exists() {
        String expectedDataHomeDirectoryPath = System
                .getProperty('user.home') +
                System.getProperty('file.separator') +
                homeDirectoryName
//        File expectedDataHomeDirectoryFile = new File(expectedDataHomeDirectoryPath)
//        if (expectedDataHomeDirectoryFile.exists() &&
//                expectedDataHomeDirectoryFile.directory) {
//            assert !this.settingService.isAppDataHomeDirectoryExists()
//        }
//        if (expectedDataHomeDirectoryFile.exists() &&
//                !expectedDataHomeDirectoryPath) {
//        }
////        log.info "DATA_HOME_DIRECTORY_PATH : " + expectedDataHomeDirectoryPath + "\t" + System.getProperty('file.separator')
    }

    @Test
    @Order(3)
    @DisplayName("testIsAppDataHomeDirExists_dir_is_file")
    void testIsAppDataHomeDirExists_dir_is_file() {
        String expectedDataHomeDirectoryPath = System
                .getProperty('user.home') +
                System.getProperty('file.separator') +
                homeDirectoryName
//        File expectedDataHomeDirectoryFile = new File(expectedDataHomeDirectoryPath)
//        if (expectedDataHomeDirectoryFile.exists() &&
//                expectedDataHomeDirectoryFile.directory) {
//            assert !this.settingService.isAppDataHomeDirectoryExists()
//        }
//        if (expectedDataHomeDirectoryFile.exists() &&
//                !expectedDataHomeDirectoryPath) {
//        }
////        log.info "DATA_HOME_DIRECTORY_PATH : " + expectedDataHomeDirectoryPath + "\t" + System.getProperty('file.separator')
    }


    @Test
    @Order(4)
    @DisplayName("createAppDataHomeDirectory")
    void createAppDataHomeDir() {
    }

    @Test
    @Order(5)
    @DisplayName("settingUpApp")
    void settingUpApp() {
    }
}