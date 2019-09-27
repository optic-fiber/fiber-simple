package com.cheroliv.fiber.inter.service

class InterTypeEnumException extends RuntimeException {
    InterTypeEnumException(String type) {
        super("Invalid type Inter : ${type}")
    }
}
