package com.cheroliv.fiber.recap.service

import com.cheroliv.fiber.inter.service.InterService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.TestMethodOrder
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest

@Slf4j
@TypeChecked
@TestMethodOrder(OrderAnnotation)
@DisplayName('RecapServiceUnitTest')
@ExtendWith(MockitoExtension)
@WebMvcTest(InterService)
class RecapServiceUnitTest {

}