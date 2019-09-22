package com.cheroliv.fiber.service

import com.cheroliv.fiber.calc.ClasseurRecap
import com.cheroliv.fiber.calc.Recap


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
