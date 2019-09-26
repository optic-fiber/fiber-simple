package com.cheroliv.fiber.inter.service

class InterNotFoundException extends RuntimeException {
    InterNotFoundException(String message) {
        super(message)
    }
}