package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.domain.Inter

interface InterDataService {
    Inter find(String nd, String type)

    Integer countMois()

    List<Map<String, Integer>> findAllMoisFormatFrParAnnee()

    String getJsonBackupFilePath()

    void importJsonFromFile()

    String buildJsonInter(Inter inter)

    void saveToJsonFile()
}
