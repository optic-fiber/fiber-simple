package com.cheroliv.fiber

import groovy.util.logging.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

@Slf4j
@SpringBootTest
@DisplayName('FiberConsoleApplicationTest')
class FiberConsoleApplicationTest implements ApplicationContextAware {

    ApplicationContext applicationContext

    @Test
    @DisplayName('contextLoads()')
    void contextLoads() {
        assert applicationContext.getBeanDefinitionCount() > 0
    }
}