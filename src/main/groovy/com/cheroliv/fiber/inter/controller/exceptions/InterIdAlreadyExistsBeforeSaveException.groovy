package com.cheroliv.fiber.inter.controller.exceptions

import org.springframework.web.bind.annotation.ResponseStatus

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY


@ResponseStatus(code = UNPROCESSABLE_ENTITY, reason = "InterventionEntity Id Already Exists Before Save")
class InterIdAlreadyExistsBeforeSaveException extends RuntimeException{
}
