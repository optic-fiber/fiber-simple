package com.cheroliv.fiber.inter.service

class InterEntityNotFoundException extends RuntimeException {
    InterEntityNotFoundException(String message) {
        super(message)
    }

    InterEntityNotFoundException(String nd, String type) {
        super("unknown inter(nd: $nd, typeInter: $type")
    }
}