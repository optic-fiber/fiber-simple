package com.cheroliv.fiber.inter.controller

import com.cheroliv.fiber.TestData
import com.cheroliv.fiber.inter.controller.exceptions.*
import com.cheroliv.fiber.inter.service.InterService
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc

import static com.cheroliv.fiber.inter.controller.InterController.INTER_BASE_URL_REST_API
import static org.mockito.BDDMockito.given
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@Slf4j
@TypeChecked
@TestMethodOrder(OrderAnnotation)
@DisplayName("InterControllerUnitTest")
@WebMvcTest(value = InterController)
class InterControllerUnitTest {

    @Autowired
    MockMvc mockMvc

    @MockBean
    InterService interService

    TestData data = TestData.getInstance()

    static String jsonFromBean(Object object) {
        new ObjectMapper().writeValueAsString(object)
    }

    @Test
    @Order(1)
    @DisplayName("testGetFirst")
    void testGetFirst() throws Exception {

        given(interService.getFirst()).willReturn(
                data.firstInterDto)

        mockMvc.perform(get("${INTER_BASE_URL_REST_API}/first"))
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

        given(interService.getFirst())
                .willThrow(FirstInterNotFoundException)

        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/first"))
                .andExpect(status().isNotFound())
    }


    @Test
    @Order(3)
    @DisplayName("testGetLast")
    void testGetLast() {
        given(interService.getLast()).willReturn(
                data.lastInterDto)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/last"))
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
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/last"))
                .andExpect(status().isNotFound())
    }


    @Test
    @Order(5)
    @DisplayName("testGet_with_param_id")
    void testGet_with_param_id() {
        given(interService.get(data.interDto.id))
                .willReturn(data.interDto)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/${data.interDto.id}"))
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
                .willThrow(InterNotFoundException)
        mockMvc.perform(get("${INTER_BASE_URL_REST_API}/${data.interDto.id}"))
                .andExpect(status().isNotFound())
    }

    @Test
    @Order(7)
    @DisplayName("testGetPrevious_with_param_id")
    void testGetPrevious_with_param_id() {
        given(interService.getPrevious(data.interDto.id))
                .willReturn(data.prevInterDto)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/${data.interDto.id}/prev"))
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
                .willThrow(PreviousInterNotFoundException)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/${data.interDto.id}/prev"))
                .andExpect(status().isNotFound())
    }

    @Test
    @Order(9)
    @DisplayName("testGetNext_with_param_id")
    void testGetNext_with_param_id() {
        given(interService.getNext(data.interDto.id))
                .willReturn(data.nextInterDto)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/${data.interDto.id}/next"))
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
                .willThrow(NextInterNotFoundException)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/${data.interDto.id}/next"))
                .andExpect(status().isNotFound())
    }


    @Test
    @Order(11)
    @DisplayName("testPostSave")
    void testPostSave() {
        given(interService.save(data.newInterDto))
                .willReturn(data.expectedPersistedInterDto)

        mockMvc.perform(post(INTER_BASE_URL_REST_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(jsonFromBean(data.newInterDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType
                        .APPLICATION_JSON_UTF8_VALUE))
                .andDo(print())
                .andExpect(jsonPath("nd")
                        .value(data.newInterDto.nd))
                .andExpect(jsonPath("typeInter")
                        .value(data.newInterDto.typeInter))
                .andExpect(jsonPath("firstName")
                        .value(data.newInterDto.firstName))
                .andExpect(jsonPath("lastName")
                        .value(data.newInterDto.lastName))
                .andReturn()
    }


    @Test
    @Order(12)
    @DisplayName("testPostSave_id_already_exists")
    void testPostSave_id_already_exists()
            throws InterAlreadyExistsException {
        given(interService.save(data.newInterDto))
                .willThrow(InterAlreadyExistsException)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/${data.interDto.id}/next"))
                .andExpect(status().isNotFound())
    }


//    @Test
//    @Disabled
//    @DisplayName("testUpdateInter")
//    void testUpdate() {
//    }


}