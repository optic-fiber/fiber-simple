package com.cheroliv.fiber.inter.controller.exceptions


import org.springframework.web.bind.annotation.ResponseStatus

import static org.springframework.http.HttpStatus.NOT_FOUND

@ResponseStatus(code=NOT_FOUND,reason="Previous Inter Not Found")
class PreviousInterNotFoundException extends RuntimeException {
}
