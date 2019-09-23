package com.cheroliv.fiber.service

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class SettingServiceImpl implements SettingService {
    @Override
    void settingUpApp() {
        log.info "${this.class.simpleName}.settingUpApp()"
        this.isAppDataHomeDirectoryExists() ?
                log.info("appDataHomeDirectoryExists : ${Boolean.TRUE}") :
                this.createAppDataHomeDirectory()
    }

    @Override
    Boolean isAppDataHomeDirectoryExists() {
        log.info("${this.class.simpleName}.isAppDataHomeDirectoryExists()")
        false
    }

    @Override
    void createAppDataHomeDirectory() {
        log.info("${this.class.simpleName}.createAppDataHomeDirectory()")

    }
}
