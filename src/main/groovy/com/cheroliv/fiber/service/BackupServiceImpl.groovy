package com.cheroliv.fiber.service

import com.cheroliv.fiber.inter.service.InterDataService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Slf4j
@Service
@TypeChecked
class BackupServiceImpl implements BackupService {

    final SettingService settingService
    final InterDataService interService
    final String homeDirectoryName
    final String jsonBackupFileName

    BackupServiceImpl(SettingService settingService,
                      InterDataService interService,
                      @Value('${application.data.home-directory-name}')
                              String homeDirectoryName,
                      @Value('${application.data.json-backup-file-name}')
                              String jsonBackupFileName) {
        this.settingService = settingService
        this.interService = interService
        this.homeDirectoryName = homeDirectoryName
        this.jsonBackupFileName = jsonBackupFileName
    }

    private String getJsonBackupFilePath() {
        System.getProperty('user.home') +
                System.getProperty("file.separator") +
                this.homeDirectoryName +
                System.getProperty("file.separator") +
                this.jsonBackupFileName
    }

    @Override
    Boolean isDataBackupFileExists() {
        log.info("${this.class.simpleName}.isDataBackupFileExists()")
        File file = new File(this.getJsonBackupFilePath())
        file.exists() && file.isFile() && !file.isDirectory()
    }

    void createDataBackupFile() {
        log.info("${this.class.simpleName}.createDataBackupFile()")
        File file = new File(this.getJsonBackupFilePath())
        if (file.exists()) {
            if (file.isDirectory()) {
                file.deleteDir()
                file.createNewFile()
            }
        } else file.createNewFile()
        assert file.exists()
        assert file.isFile()
        assert !file.isDirectory()
    }


    @Override
    void loadBackupInDatabase() {
        log.info("${this.class.simpleName}" +
                ".loadBackupInDatabase()")
        this.settingService.settingUpApp()
        this.isDataBackupFileExists() ?:
                this.createDataBackupFile()
        this.interService.importJsonFromFile(
                this.getJsonBackupFilePath())
    }
}
