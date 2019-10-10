package com.cheroliv.fiber.inter.service


import com.cheroliv.fiber.inter.domain.InterventionEntity

interface InterDataService {
    InterventionEntity find(String nd, String type)

    Integer countMois()

    List<Map<String, Integer>> findAllMoisFormatFrParAnnee()

    String getJsonBackupFilePath()

    void importJsonFromFile()

    String buildJsonInter(InterventionEntity inter)

    void saveToJsonFile()
}
