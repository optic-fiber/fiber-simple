package com.cheroliv.fiber.console.dao


import org.hibernate.dialect.H2Dialect

import java.sql.Types

class H2Dialect_ extends H2Dialect {

    H2Dialect_() {
        super()
        registerColumnType Types.FLOAT, "real"
    }
}
