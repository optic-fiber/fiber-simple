package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.dto.InterventionDto

interface InterService {
    InterventionDto find(String nd, String type)

    InterventionDto getFirst()

    InterventionDto getPrevious(Long id)

    InterventionDto save(InterventionDto interDto)

    InterventionDto getNext(Long id)

    InterventionDto getLast()

    InterventionDto get(Long id)

    Boolean isUniqueIndexAvailable(String nd, String type)

    void delete(Long id)

    InterventionDto findById(Long id)

    Boolean isUniqueIndexConsistent(Long id, String nd, String type)

    InterventionDto saveWithPatch(InterventionDto interDto)

    List<InterventionDto> getAll()
}
