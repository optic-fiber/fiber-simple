package com.cheroliv.fiber.inter.controller.exceptions

import org.springframework.web.bind.annotation.ResponseStatus

import static org.springframework.http.HttpStatus.CONFLICT

@ResponseStatus(code = CONFLICT, reason = "Inter Id Already Exists")
class InterAlreadyExistsException extends RuntimeException {

}
