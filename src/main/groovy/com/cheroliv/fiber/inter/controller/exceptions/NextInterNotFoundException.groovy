package com.cheroliv.fiber.inter.controller.exceptions

import org.springframework.web.bind.annotation.ResponseStatus

import static org.springframework.http.HttpStatus.NOT_FOUND

@ResponseStatus(code=NOT_FOUND,reason ="Next InterventionEntity Not Found")
class NextInterNotFoundException extends RuntimeException {
}
