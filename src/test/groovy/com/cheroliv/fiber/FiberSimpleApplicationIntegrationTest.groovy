package com.cheroliv.fiber

import com.fasterxml.jackson.databind.ObjectMapper
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DisplayName('FiberSimpleApplicationIntegrationTest')
class FiberSimpleApplicationIntegrationTest implements ApplicationContextAware {

    ApplicationContext applicationContext
    @Autowired
    ObjectMapper mapper

    /**
     * sert a creer mon json pour mes tests unitaire
     * le mapper injecté contient une configuration pas
     * disponible pas simple instantiation
     */
    String jsonFromBean(Object object) {
        mapper.writeValueAsString(object)
    }

    @Test
    @DisplayName('contextLoads()')
    void contextLoads() {
        assert applicationContext.getBeanDefinitionCount() > 0
    }
}