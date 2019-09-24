package com.cheroliv.fiber.service

import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Slf4j
@Service
@TypeChecked
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
        file.exists() && file.isDirectory() && !file.isFile()
    }


    @Override
    void createAppDataHomeDirectory() {
        log.info("${this.class.simpleName}.createAppDataHomeDirectory()")
        File dir = new File(this.getDataHomeDirectoryPath())
        dir.mkdir()
        this.dataHomeDirectoryAssertions()
    }

    private void deleteAppDataHomeDirectoryIfExistsAsFile() {
        File file = new File(this.getDataHomeDirectoryPath())
        if (file.exists()) {
            if (file.isFile()) assert file.delete()
            else assert !file.isFile() && file.isDirectory()
        }
    }

    private void dataHomeDirectoryAssertions() {
        File file = new File(this.getDataHomeDirectoryPath())
        assert file.exists()
        assert file.isDirectory()
        assert !file.isFile()
    }

    @Override
    void settingUpApp() {
        log.info "${this.class.simpleName}.settingUpApp()"
        this.deleteAppDataHomeDirectoryIfExistsAsFile()
        this.isAppDataHomeDirectoryExists() ?
                log.info("appDataHomeDirectoryExists : ${Boolean.TRUE}") :
                this.createAppDataHomeDirectory()
    }
}
