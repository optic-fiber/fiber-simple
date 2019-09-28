package com.cheroliv.fiber.inter.controller

import com.cheroliv.fiber.TestData
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

import static org.mockito.BDDMockito.given


//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
//import static org.hamcrest.CoreMatchers.containsString

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

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
    TestData data = TestData.getInstance()


    @Test
    @Order(1)
    @DisplayName("testGetFirst")
    void testGetFirst() throws Exception {

        //---------------------------//
        //1)======ARRANGE======(finish)
        //---------------------------//
        given(interService.getFirst()).willReturn(
                data.firstInterDto)
        //---------------------------//
        //2)=========ACT========(start)
        //---------------------------//
        mockMvc.perform(get("/inters/first"))
        //---------------------------//
        //3)========ASSERT=======(then)
        //---------------------------//
                .andExpect(jsonPath("id")
                        .value(data.firstInterDto.id))
                .andExpect(jsonPath("nd")
                        .value(data.firstInterDto.nd))
                .andExpect(jsonPath("typeInter")
                        .value(data.firstInterDto.typeInter))
                .andExpect(status().isOk())

    }

    @Test
    @Order(2)
    @DisplayName("testGetFirst_not_found")
    void testGetFirst_not_found()
            throws FirstInterNotFoundException {
        //ARRANGE
        given(interService.getFirst())
                .willThrow(FirstInterNotFoundException)
        //ACT
        mockMvc.perform(get('/inters/first'))
        //ASSERT
                .andExpect(status().isNotFound())
    }


    @Test
    @Order(3)
    @DisplayName("testGetLast")
    void testGetLast() {
        given(interService.getLast()).willReturn(
                data.lastInterDto)
        mockMvc.perform(get("/inters/last"))
                .andExpect(jsonPath("id")
                        .value(data.lastInterDto.id))
                .andExpect(jsonPath("nd")
                        .value(data.lastInterDto.nd))
                .andExpect(jsonPath("typeInter")
                        .value(data.lastInterDto.typeInter))
                .andExpect(status().isOk())
    }


    @Test
    @Order(4)
    @DisplayName("testGetLast_not_found")
    void testGetLast_not_found()
            throws LastInterNotFoundException {
        given(interService.getLast())
                .willThrow(LastInterNotFoundException)
        mockMvc.perform(get('/inters/last'))
                .andExpect(status().isNotFound())
    }


    @Test
    @Order(5)
    @DisplayName("testGet_with_param_id")
    void testGet_with_param_id() {
        given(interService.get(data.interDto.id))
                .willReturn(data.interDto)
        mockMvc.perform(get(
                "/inters/${data.interDto.id}"))
                .andExpect(jsonPath("id")
                        .value(data.interDto.id))
                .andExpect(jsonPath("nd")
                        .value(data.interDto.nd))
                .andExpect(jsonPath("typeInter")
                        .value(data.interDto.typeInter))
                .andExpect(status().isOk())
    }


    @Test
    @Order(6)
    @DisplayName("testGetInter_with_param_id_not_found")
    void testGetInter_with_param_id_not_found()
            throws InterNotFoundException {
        given(interService.get(data.interDto.id))
                .willThrow(InterNotFoundException )
        mockMvc.perform(get("/inters/${data.interDto.id}"))
                .andExpect(status().isNotFound())
    }

    @Test
    @Order(7)
    @DisplayName("testGetPrevious_with_param_id")
    void testGetPrevious_with_param_id() {
        given(interService.getPrevious(data.interDto.id))
        .willReturn(data.prevInterDto)
        mockMvc.perform(get(
                "/inters/${data.interDto.id}/prev"))
                .andExpect(jsonPath("id")
                        .value(data.prevInterDto.id))
                .andExpect(jsonPath("nd")
                        .value(data.prevInterDto.nd))
                .andExpect(jsonPath("typeInter")
                        .value(data.prevInterDto.typeInter))
                .andExpect(status().isOk())
    }

    @Test
    @Order(8)
    @DisplayName("testGetPrevious_with_param_id_not_found")
    void testGetPrevious_with_param_id_not_found()
            throws PreviousInterNotFoundException {
        given(interService.getPrevious(data.interDto.id))
                .willThrow(PreviousInterNotFoundException )
        mockMvc.perform(get(
                "/inters/${data.interDto.id}/prev"))
                .andExpect(status().isNotFound())
    }

    @Test
    @Order(9)
    @DisplayName("testGetNext_with_param_id")
    void testGetNext_with_param_id() {
        given(interService.getNext(data.interDto.id))
                .willReturn(data.nextInterDto)
        mockMvc.perform(get(
                "/inters/${data.interDto.id}/next"))
                .andExpect(jsonPath("id")
                        .value(data.nextInterDto.id))
                .andExpect(jsonPath("nd")
                        .value(data.nextInterDto.nd))
                .andExpect(jsonPath("typeInter")
                        .value(data.nextInterDto.typeInter))
                .andExpect(status().isOk())
    }

    @Test
    @Order(10)
    @DisplayName("testGetNext_with_param_id_not_found")
    void testGetNext_with_param_id_not_found()
            throws NextInterNotFoundException {
        given(interService.getNext(data.interDto.id))
                .willThrow(NextInterNotFoundException )
        mockMvc.perform(get(
                "/inters/${data.interDto.id}/next"))
                .andExpect(status().isNotFound())
    }


    @Test
    @Disabled
    @DisplayName("testPostCreate")
    void testPostCreate() {
    }


    @Test
    @Disabled
    @DisplayName("testUpdateInter")
    void testUpdate() {
    }


}