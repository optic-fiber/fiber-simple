package com.cheroliv.fiber.recap.model

import groovy.transform.ToString

import java.time.LocalDateTime

@ToString
class RecapRequestDto {
    LocalDateTime startDate
    LocalDateTime endDate
}
