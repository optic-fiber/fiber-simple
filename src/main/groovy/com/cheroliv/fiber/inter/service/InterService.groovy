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

    InterDto isUniqueKey(String nd, String type)
}