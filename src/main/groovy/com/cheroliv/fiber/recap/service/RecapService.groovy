package com.cheroliv.fiber.recap.service

import com.cheroliv.fiber.recap.spreadsheet.ClasseurRecap
import com.cheroliv.fiber.recap.model.Recap


interface RecapService {
    void setPath(String path)

    ClasseurRecap init()

    Recap processRecap(
            String nomFeuilles,
            Integer moisInt,
            Integer anneeInt)

    ClasseurRecap processFeuilles()

    ClasseurRecap processClasseurFeuilles(String classeurPath)

    List<String> nomFeuilles()
}
