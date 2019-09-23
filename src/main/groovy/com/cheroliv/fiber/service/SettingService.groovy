package com.cheroliv.fiber.service

interface SettingService {
    void settingUpApp()
    Boolean isAppDataHomeDirectoryExists()
    void createAppDataHomeDirectory()
}