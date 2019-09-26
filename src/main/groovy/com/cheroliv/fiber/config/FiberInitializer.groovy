package com.cheroliv.fiber.config

import com.cheroliv.fiber.service.BackupService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.BeanFactoryAware
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
    /**
     * Invoked by the containing {@code BeanFactory} after it has set all bean properties
     * and satisfied {@link BeanFactoryAware}, {@code ApplicationContextAware} etc.
     * <p>This method allows the bean instance to perform validation of its overall
     * configuration and final initialization when all bean properties have been set.
     * @throws Exception in the event of misconfiguration (such as failure to set an
     * essential property) or if initialization fails for any other reason
     */
    @Override
    void afterPropertiesSet() throws Exception {
        log.info """AVANT backupService.loadBackupInDatabase()"""
        this.backupService.loadBackupInDatabase()
        log.info """APRES backupService.loadBackupInDatabase()"""
    }
}