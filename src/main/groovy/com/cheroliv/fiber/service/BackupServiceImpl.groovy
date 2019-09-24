package com.cheroliv.fiber.service

import com.cheroliv.fiber.inter.service.InterService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

@Slf4j
@Service
@TypeChecked
class BackupServiceImpl implements BackupService {

    final SettingService settingService
    final InterService interService
    final String homeDirectoryName
    final String jsonBackupFileName

    @Autowired
    BackupServiceImpl(SettingService settingService,
                      InterService interService,
                      @Value('${application.data.home-directory-name}')
                              String homeDirectoryName,
                      @Value('${application.data.json-backup-file-name}')
                              String jsonBackupFileName) {
        this.settingService = settingService
        this.interService = interService
        this.homeDirectoryName = homeDirectoryName
        this.jsonBackupFileName = jsonBackupFileName
    }

    @Override
    Boolean isDataBackupFileExists() {
        log.info("${this.class.simpleName}.isDataBackupFileExists()")
        false
    }

    void createDataBackupFile() {
        log.info("${this.class.simpleName}.createDataBackupFile()")
    }

    private String getJsonBackupFilePath() {
        System.getProperty('user.home') +
                this.homeDirectoryName +
                System.getProperty("path.separator") +
                this.jsonBackupFileName
    }

    @Override
    @PostConstruct
    void loadBackupInDatabase() {
        log.info("${this.class.simpleName}" +
                ".loadBackupInDatabase()")
        this.settingService.settingUpApp()
        this.isDataBackupFileExists() ?:
                this.createDataBackupFile()
        try {
            this.interService
                    .importJsonFromFile(
                            this.getJsonBackupFilePath())
        } catch (IOException ioe) {
            log.debug(ioe.stackTrace.toArrayString())
        }
    }
}
