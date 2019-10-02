package com.cheroliv.fiber.inter.service.exceptions

class InterEntityNotFoundException extends RuntimeException {
    InterEntityNotFoundException(String message) {
        super(message)
    }

    InterEntityNotFoundException(String nd, String type) {
        super("unknown inter(nd: $nd, typeInter: $type")
    }
}