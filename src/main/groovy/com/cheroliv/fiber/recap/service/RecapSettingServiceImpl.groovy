package com.cheroliv.fiber.recap.service

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class RecapSettingServiceImpl implements RecapSettingService {
    @Value('${application.data.home-directory-name}')
    String homeDirectoryName

    String getHomeDataDirectoryPath() {
        new File(System.getProperty('user.home') +
                System.getProperty('file.separator') +
                homeDirectoryName).path
    }

    void createHomeDataDirectory() {
        File file = new File(getHomeDataDirectoryPath())
        if (!file.exists()) file.createNewFile()
        else if (file.isFile()) {
            file.delete()
            file.mkdir()
        }
    }


    @Override
    Boolean checkDataHomeDirectoryExists() {
        File file = new File(this.getHomeDataDirectoryPath())
        if (!file.exists() ||
                (file.exists() && file.isFile()))
            createHomeDataDirectory()
        return file.exists() && file.isDirectory()
    }
}
