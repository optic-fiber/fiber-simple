package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.model.InterDto

interface InterService {
    InterDto getFirst()

    InterDto getPrevious(Long id)

    InterDto create(InterDto interDto)

    InterDto update(InterDto interDto)

    InterDto getNext(Long id)

    InterDto getLast()

    InterDto get(Long id)
}