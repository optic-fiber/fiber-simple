package com.cheroliv.fiber.console

import com.cheroliv.fiber.console.config.ApplicationProperties
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ConfigurableApplicationContext

@Slf4j
@CompileStatic
@SpringBootApplication
@EnableConfigurationProperties(ApplicationProperties)
class FiberConsoleApplication {
    static void main(String[] args) {
        SpringApplicationBuilder builder =
                new SpringApplicationBuilder(FiberConsoleApplication)
        builder.headless(false)
        ConfigurableApplicationContext context = builder.run(args)
    }
}
