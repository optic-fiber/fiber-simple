package com.cheroliv.fiber

import groovy.util.logging.Slf4j
import org.springframework.boot.CommandLineRunner
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Slf4j
@Component
class FiberCommandLineRunner implements CommandLineRunner, ApplicationContextAware {

    ApplicationContext applicationContext

    FiberCommandLineRunner(ApplicationContext ac) {
        this.applicationContext = ac
    }


    @Override
    void run(String... args) throws Exception {
        log.info "bean provided by spring container : ${applicationContext.getBeanDefinitionNames().toArrayString()}"
    }
}
