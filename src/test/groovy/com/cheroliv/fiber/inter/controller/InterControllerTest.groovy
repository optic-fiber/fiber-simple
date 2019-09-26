package com.cheroliv.fiber.inter.controller

import com.cheroliv.fiber.inter.service.InterService
import com.cheroliv.fiber.inter.service.InterDataService
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc

@Slf4j
@WebMvcTest(InterController)
@TestMethodOrder(OrderAnnotation)
@DisplayName("BackupServiceImplTest")
class InterControllerTest {

//    @MockBean
//    InterService interResource
//    @Autowired
//    MockMvc mockMvc
//
//
//    @Test
//    @Order(1)
//    @Disabled
//    @DisplayName("testFirstInter")
//    void testFirstInter() {
//    }
//
//    @Test
//    @Order(2)
//    @Disabled
//    @DisplayName("testPreviousInter")
//    void testPreviousInter() {
//    }
//
//    @Test
//    @Order(3)
//    @Disabled
//    @DisplayName("testCreateInter")
//    void testCreateInter() {
//    }
//
//    @Test
//    @Order(4)
//    @Disabled
//    @DisplayName("testUpdateInter")
//    void testUpdateInter() {
//    }
//
//    @Test
//    @Order(5)
//    @Disabled
//    @DisplayName("testNextInter")
//    void testNextInter() {
//    }
//
//    @Test
//    @Order(6)
//    @Disabled
//    @DisplayName("testLastInter")
//    void testLastInter() {
//    }
}
