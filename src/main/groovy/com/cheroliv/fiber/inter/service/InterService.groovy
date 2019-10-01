package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.model.InterDto

interface InterService {
    InterDto getFirst()

    InterDto getPrevious(Long id)

    InterDto save(InterDto interDto)

    InterDto getNext(Long id)

    InterDto getLast()

    InterDto get(Long id)

    Boolean isUniqueIndexAvailable(String nd, String type)

    void delete(Long id)

    InterDto findById(Long id)

    Boolean isUniqueIndexConsistent(Long id, String nd, String type)

    InterDto saveWithPatch(InterDto interDto)
}
