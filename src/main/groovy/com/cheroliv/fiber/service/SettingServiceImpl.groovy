package com.cheroliv.fiber.service

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Slf4j
@Service
class SettingServiceImpl implements SettingService {

    final String homeDirectoryName

    SettingServiceImpl(@Value('${application.data.home-directory-name}')
                               String homeDirectoryName) {
        this.homeDirectoryName = homeDirectoryName
    }

    private String getDataHomeDirectoryPath() {
        System.getProperty('user.home') +
                System.getProperty('file.separator') +
                homeDirectoryName
    }

    @Override
    boolean isAppDataHomeDirectoryExists() {
        log.info("${this.class.simpleName}.isAppDataHomeDirectoryExists()")
        File file = new File(this.getDataHomeDirectoryPath())
        file.exists() && file.directory && !file.file
    }


    @Override
    void settingUpApp() {
        log.info "${this.class.simpleName}.settingUpApp()"
        this.isAppDataHomeDirectoryExists() ?
                log.info("appDataHomeDirectoryExists : ${Boolean.TRUE}") :
                this.createAppDataHomeDirectory()
//        throw new NotImplementedException("${this.class.simpleName}.settingUpApp()")
    }



    @Override
    void createAppDataHomeDirectory() {
        log.info("${this.class.simpleName}.createAppDataHomeDirectory()")
//        throw new NotImplementedException("${this.class.simpleName}.createAppDataHomeDirectory()")
    }
}
