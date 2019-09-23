package com.cheroliv.fiber.config

interface ApplicationDefaults {
    interface Classeur {
        String directoryName = "output"
        String pathName = "recapClasseur.xlsx"
    }

    interface Data {
        String directoryName = "fiber-simple"
        String jsonBackupFileName = "inters.json"
    }
}