package com.cheroliv.fiber.inter.domain.enumeration

import groovy.transform.CompileStatic

@CompileStatic
enum InterTypeEnum {
    BAAP, BAOC, BAFA, BAST, PLP, SAV

    static InterTypeEnum valueOfName(String name) {
        values().find { InterTypeEnum it ->
            it.name() == name
        }
    }
}
