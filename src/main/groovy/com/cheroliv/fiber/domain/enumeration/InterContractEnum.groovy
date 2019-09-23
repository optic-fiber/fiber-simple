package com.cheroliv.fiber.domain.enumeration

import groovy.transform.CompileStatic


@CompileStatic
enum InterContractEnum {
    LM, IQ, CABLE_ROUTING
    static InterContractEnum valueOfName(String name ) {
        values().find { InterContractEnum it ->
            it.name() == name
        }
    }
}
