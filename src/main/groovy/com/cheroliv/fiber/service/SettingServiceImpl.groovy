package com.cheroliv.fiber.service

import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service

@Slf4j
@Service
class SettingServiceImpl implements SettingService {
    @Override
    void settingUpApp() {
        log.info "SettingServiceImpl.settingUpApp()"
        this.isAppDataHomeDirExists() ?
                log.info("AppDataHomeDirExists : ${Boolean.TRUE}") :
                this.createAppDataHomeDir()
    }

    @Override
    Boolean isAppDataHomeDirExists() {
        false
    }

    @Override
    void createAppDataHomeDir() {
        log.info("createAppDataHomeDir()")

    }
}
