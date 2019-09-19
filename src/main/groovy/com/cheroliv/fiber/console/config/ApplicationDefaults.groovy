package com.cheroliv.fiber.console.config

interface ApplicationDefaults {
    interface Classeur {
        String directoryName = "output"
        String pathName = "recapClasseur.xlsx"
    }

    interface Data {
        String directoryName = ".fiber"
        String jsonBackupFileName = "inter.json"
    }
}