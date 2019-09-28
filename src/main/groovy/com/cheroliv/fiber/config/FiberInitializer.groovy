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
        def trace1 = "${this.class.simpleName.toUpperCase()}."
        def trace2 = "afterPropertiesSet()".toUpperCase()
        log.info(trace1+trace2+" : INSIDE")
        try {
            this.backupService.loadBackupInDatabase()
        } catch (Throwable e) {
            throw new FiberInitializerException(cause: e)
        }
        log.info(trace1+trace2+" : OUTSIDE")

    }

}