package fiber.service

import fiber.calc.ClasseurRecap
import fiber.calc.Recap


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
