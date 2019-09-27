package com.cheroliv.fiber.config

import com.cheroliv.fiber.service.BackupService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Component

@Slf4j
@Component
@TypeChecked
class FiberInitializer implements InitializingBean {

    final BackupService backupService

    FiberInitializer(BackupService backupService) {
        this.backupService = backupService
    }

    @Override
    void afterPropertiesSet() throws Exception {
        try {
            this.backupService.loadBackupInDatabase()
        } catch (Throwable e) {
            throw new FiberInitializerException(cause: e)
        }
    }

}