package com.cheroliv.fiber.inter.dao

import com.cheroliv.fiber.inter.dto.InterDto

interface InterventionRepository {
    InterDto save(InterDto dto)
}