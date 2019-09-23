package com.cheroliv.fiber.service

import com.cheroliv.fiber.inter.service.InterService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

import javax.annotation.PostConstruct

@Slf4j
@Service
class BackupServiceImpl implements BackupService {

    final SettingService settingService
    final InterService interService
    @Value('${}')
    final String appDataDataHome
    @Value('${}')
    final String interJsonFileName

    @Autowired
    BackupServiceImpl(SettingService settingService,
                      InterService interService,
                      @Value('${}')
                              String appDataDataHome,
                      @Value('${}')
                              String interJsonFileName) {
        this.settingService = settingService
        this.interService = interService
        this.appDataDataHome = appDataDataHome
        this.interJsonFileName = interJsonFileName
    }

    @Override
    Boolean isDataBackupFileExists() {
        false
    }

    void createDataBackupFile() {
        log.info("createDataBackupFile()")
    }

    @Override
    @PostConstruct
    void loadBackupInDatabase() {
        this.settingService.settingUpApp()
        this.isDataBackupFileExists() ?:
                this.createDataBackupFile()
        try {
            this.interService.importJsonFromFile(
                    this.getProperty("user.home") +
                            this.appDataDataHome +
                            System.getProperty("path.separator") +
                            this.interJsonFileName
            )
        } catch (IOException ioe) {
            log.info(ioe.stackTrace.toArrayString())
        }

    }
}
