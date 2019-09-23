package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.domain.Inter


interface InterService {
    Inter find(String nd, String type)

    Integer countMois()

    List<Map<String, Integer>> findAllMoisFormatFrParAnnee()

    String getFiberJsonFilePath(String baseFolderPath)

    void setUp()

    void importJsonFromFile(String path) throws IOException

    String buildJsonInter(Inter inter)

    void saveToJsonFile(String path) throws IOException
}
