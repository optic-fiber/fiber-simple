package com.cheroliv.fiber

import com.cheroliv.fiber.config.ApplicationProperties
import com.cheroliv.fiber.config.JacksonConfig
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Import

@SpringBootApplication
@Import(JacksonConfig)
@EnableConfigurationProperties(ApplicationProperties)
class FiberSimpleApplication {
    static void main(String[] args) {
        SpringApplication.run(FiberSimpleApplication, args)
    }
}
