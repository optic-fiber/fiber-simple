package com.cheroliv.fiber.console.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component


@Component
@ConfigurationProperties(
        prefix = 'application',
        ignoreUnknownFields = false)
class ApplicationProperties {
    final Classeur classeur = new Classeur()
    final Data data = new Data()

    public static class Classeur {
        String directoryName = ApplicationDefaults
                .Classeur
                .name
        String pathName = ApplicationDefaults
                .Classeur
                .pathName
    }

    public static class Data {
        String directoryName = ApplicationDefaults
                .Data
                .directoryName
        String jsonBackupFileName = ApplicationDefaults
                .Data
                .jsonBackupFileName
    }
}

