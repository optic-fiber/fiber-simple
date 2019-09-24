package com.cheroliv.fiber.service

interface SettingService {
    void settingUpApp()
    boolean isAppDataHomeDirectoryExists()
    void createAppDataHomeDirectory()
}