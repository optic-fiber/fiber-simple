package com.cheroliv.fiber.service

interface BackupService {
    /**
     * ie is inters.json file exists
     * @return
     */
    Boolean isDataBackupFileExists()
    void loadBackupInDatabase()
    void createDataBackupFile()
}