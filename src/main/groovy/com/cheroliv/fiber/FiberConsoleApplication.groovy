package com.cheroliv.fiber

import com.cheroliv.fiber.config.ApplicationProperties
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties

@Slf4j
@CompileStatic
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties)
class FiberConsoleApplication {
    static void main(String[] args) {
        SpringApplication.run(FiberConsoleApplication, args)
    }
}
