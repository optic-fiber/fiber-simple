package com.cheroliv.fiber.inter.dao

import com.cheroliv.fiber.inter.dto.InterventionDto

interface InterventionRepository {
    InterventionDto save(InterventionDto dto)
}