package com.cheroliv.fiber.inter.controller

import org.springframework.web.bind.annotation.ResponseStatus

import static org.springframework.http.HttpStatus.NOT_FOUND

@ResponseStatus(NOT_FOUND)
class NextInterNotFoundException extends RuntimeException {
}
