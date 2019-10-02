package com.cheroliv.fiber.inter.service.exceptions

class InterTypeEnumException extends RuntimeException {
    InterTypeEnumException(String type) {
        super("Invalid type Inter : ${type}")
    }
}
