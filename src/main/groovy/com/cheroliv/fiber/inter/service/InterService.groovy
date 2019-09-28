package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.model.InterDto

interface InterService {
    InterDto getFirst()

    InterDto getPrevious(Long id)

    void create(InterDto interDto)

    void update(InterDto interDto)

    InterDto getNext(Long id)

    InterDto getLast()

    InterDto get(Long id)
}