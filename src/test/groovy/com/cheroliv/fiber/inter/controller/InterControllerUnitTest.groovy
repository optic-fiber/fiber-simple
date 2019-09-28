package com.cheroliv.fiber.inter.controller


import com.cheroliv.fiber.inter.model.InterDto
import com.cheroliv.fiber.inter.service.InterService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.MockBeans
import org.springframework.test.web.servlet.MockMvc

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
//import static org.hamcrest.CoreMatchers.containsString
import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath

@Slf4j
@TypeChecked
@TestMethodOrder(OrderAnnotation)
@DisplayName("InterControllerUnitTest")
@WebMvcTest(value = InterController)
@MockBeans([@MockBean(InterService)])
class InterControllerUnitTest {

    @Autowired
    MockMvc mockMvc

    @MockBean
    InterService interService

    InterDto firstInterDto = new InterDto(
            id: 1, nd: "0144639035",
            lastName: "Lalande", firstName: "Julien",
            dateTime: strToDateTime("2018-10-29",
                    "10:00:00"),
            contract: "IQ", typeInter: "BAOC")


    InterDto lastInterDto = new InterDto(
            id: 109, nd: "0143485957",
            lastName: "Bouvier", firstName: "Steven",
            dateTime: strToDateTime("2019-01-04",
                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")

    @Test
    @Order(1)
    @DisplayName("testGetFirstInter")
    void testGetFirstInter() throws Exception {

        //---------------------------//
        //1)======ARRANGE======(finish)
        //---------------------------//
        given(interService.getFirst()).willReturn(firstInterDto)
        //---------------------------//
        //2)=========ACT========(start)
        //---------------------------//
        mockMvc.perform(get("/inters/first"))
        //---------------------------//
        //3)========ASSERT=======(then)
        //---------------------------//
                .andExpect(jsonPath("id")
                        .value(firstInterDto.id))
                .andExpect(jsonPath("nd")
                        .value(firstInterDto.nd))
                .andExpect(jsonPath("typeInter")
                        .value(firstInterDto.typeInter))
                .andExpect(status().isOk())

    }

    @Test
    @Order(2)
    @DisplayName("testGetFirstInter_not_found")
    void testGetFirstInter_not_found() throws FirstInterNotFoundException {
        //ARRANGE
        given(interService.getFirst()).willThrow(FirstInterNotFoundException)
        //ACT
        mockMvc.perform(get('/inters/first'))
        //ASSERT
                .andExpect(status().isNotFound())
    }


    @Test
    @Order(3)
    @DisplayName("testGetLastInter")
    void testGetLastInter() {
        given(interService.getLast()).willReturn(lastInterDto)
        mockMvc.perform(get("/inters/last"))
                .andExpect(jsonPath("id")
                        .value(lastInterDto.id))
                .andExpect(jsonPath("nd")
                        .value(lastInterDto.nd))
                .andExpect(jsonPath("typeInter")
                        .value(lastInterDto.typeInter))
                .andExpect(status().isOk())
    }


    @Test
    @Order(4)
    @DisplayName("testGetLastInter_not_found")
    void testGetLastInter_not_found() throws LastInterNotFoundException{
        given(interService.getLast()).willThrow(LastInterNotFoundException)
        mockMvc.perform(get('/inters/last'))
                .andExpect(status().isNotFound())

    }


    @Test
    @Disabled
    @DisplayName("testGetInter")
    void testGetInter() {
    }


    @Test
    @Disabled
    @DisplayName("testGetInter_not_found")
    void testGetInter_not_found() {
    }


    @Test
    @Disabled
    @DisplayName("testFirstInter")
    void testFirstInter() {
        String requestUrl = "GET:api/inters/{id}"
    }

    @Test
    @Disabled
    @DisplayName("testPreviousInter")
    void testPreviousInter() {
    }


    @Test
    @Disabled
    @DisplayName("testNextInter")
    void testNextInter() {
    }


    @Test
    @Disabled
    @DisplayName("testCreateInter")
    void testCreateInter() {
    }


    @Test
    @Disabled
    @DisplayName("testUpdateInter")
    void testUpdateInter() {
    }


    private static ZonedDateTime strToDateTime(String date, String time) {
        ZonedDateTime.of(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss")),
                ZoneId.systemDefault())
    }
}
//    @Autowired
//    InterRepository interRepository

//log.info "interRepository.count().toString() : "
//        .toUpperCase() +
//        interRepository.count().toString()
