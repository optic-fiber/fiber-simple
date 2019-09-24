package com.cheroliv.fiber

import com.cheroliv.fiber.config.ApplicationProperties
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties)
class FiberSimpleApplication {
    static void main(String[] args) {
        SpringApplication.run(FiberSimpleApplication, args)
    }
}
