package com.cheroliv.fiber.inter.service

class InterNotFoundException extends RuntimeException {
    InterNotFoundException(String message) {
        super(message)
    }
    InterNotFoundException(String nd,String type) {
        super("unknown inter(nd: $nd, typeInter: $type")
    }
}