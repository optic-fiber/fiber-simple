package com.cheroliv.fiber.inter.controller

import com.cheroliv.fiber.inter.service.InterService
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@Slf4j
@WebMvcTest(value = InterController)
@TestMethodOrder(OrderAnnotation)
@DisplayName("BackupServiceImplTest")
class InterControllerTest {

    @Autowired
    MockMvc mockMvc

    @MockBean
    InterService interService

    @InjectMocks
    InterController interController


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    @Order(1)
    @DisplayName("testCreateInter")
    void testCreateInter() {
    }


    @Test
    @Order(2)
    @DisplayName("testFirstInter")
    void testFirstInter() {
        String requestUrl = "GET:api/inters/{id}"
    }

    @Test
    @Order(2)
    @DisplayName("testPreviousInter")
    void testPreviousInter() {
    }


    @Test
    @Order(4)
    @DisplayName("testUpdateInter")
    void testUpdateInter() {
    }

    @Test
    @Order(5)
    @DisplayName("testNextInter")
    void testNextInter() {
    }

    @Test
    @Order(6)
    @DisplayName("testLastInter")
    void testLastInter() {
    }
}
