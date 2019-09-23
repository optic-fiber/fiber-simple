package com.cheroliv.fiber.service

interface SettingService {
    void settingUpApp()
    Boolean isAppDataHomeDirExists()
    void createAppDataHomeDir()
}