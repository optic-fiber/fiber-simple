package com.cheroliv.fiber.console


import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.springframework.boot.CommandLineRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Component

@Slf4j
@Component
@CompileStatic
@Profile("!test")
class FiberCommandLineRunner implements
        CommandLineRunner,
        ApplicationContextAware {

    ApplicationContext applicationContext

    @Override
    void run(String... strings) throws Exception {

        if (!applicationContext.environment.activeProfiles.contains("test")) {
            String path = System.getProperty("user.home")
            log.info "PATH:\t$path"
//            InterService interService = applicationContext.getBean(InterService)
//            RecapService recapService = applicationContext.getBean(RecapService)
//            interService.setUp()
//            interService.importJsonFromFile interService.getFiberJsonFilePath(path)
//            recapService.processClasseurFeuilles(path)
            //           interService.saveToJsonFile interService.getFiberJsonFilePath(path)

        }
    }
}
