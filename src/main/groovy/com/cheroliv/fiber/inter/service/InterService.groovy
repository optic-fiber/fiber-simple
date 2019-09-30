package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.model.InterDto

interface InterService {
    InterDto getFirst()

    InterDto getPrevious(Long id)

    InterDto save(InterDto interDto)

    InterDto update(InterDto interDto)

    InterDto getNext(Long id)

    InterDto getLast()

    InterDto get(Long id)

    Boolean isUniqueKey(String nd, String type)

    void delete(Long id)

    InterDto findById(Long id)
}