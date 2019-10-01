package com.cheroliv.fiber.inter.controller

import com.cheroliv.fiber.TestData
import com.cheroliv.fiber.inter.controller.exceptions.*
import com.cheroliv.fiber.inter.service.InterService
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
import org.springframework.test.web.servlet.MockMvc

import static com.cheroliv.fiber.inter.controller.InterController.INTER_BASE_URL_REST_API
import static org.mockito.ArgumentMatchers.anyLong
import static org.mockito.ArgumentMatchers.anyString
import static org.mockito.BDDMockito.given
import static org.mockito.Mockito.*
import static org.springframework.http.MediaType.APPLICATION_JSON
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
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


    @Test
    @Order(1)
    @DisplayName("testGetFirst")
    void testGetFirst() throws Exception {
        given(interService.getFirst()).willReturn(
                data.firstInterDto)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/first"))
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
                "$INTER_BASE_URL_REST_API/{id}",
                data.interDto.id))
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
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/{id}",
                data.interDto.id))
                .andExpect(status().isNotFound())
    }

    @Test
    @Order(7)
    @DisplayName("testGetPrevious_with_param_id")
    void testGetPrevious_with_param_id() {
        given(interService.getPrevious(data.interDto.id))
                .willReturn(data.prevInterDto)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/{id}/prev",
                data.interDto.id))
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
                "${INTER_BASE_URL_REST_API}/{is}/prev",
                data.interDto.id))
                .andExpect(status().isNotFound())
    }

    @Test
    @Order(9)
    @DisplayName("testGetNext_with_param_id")
    void testGetNext_with_param_id() {
        given(interService.getNext(data.interDto.id))
                .willReturn(data.nextInterDto)
        mockMvc.perform(get(
                "${INTER_BASE_URL_REST_API}/{id}/next",
                data.interDto.id))
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
                "${INTER_BASE_URL_REST_API}/{id}/next",
                data.interDto.id))
                .andExpect(status().isNotFound())
    }


    @Test
    @Order(11)
    @DisplayName("testPostSave")
    void testPostSave() {
        given(interService.save(data.newInterDto))
                .willReturn(data.expectedPersistedInterDto)
        mockMvc.perform(post(INTER_BASE_URL_REST_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(data.jsonNewtInterDto))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(
                        APPLICATION_JSON_UTF8_VALUE))
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
    @DisplayName("testPostSave_id_already_exists_before_save")
    void testPostSave_id_already_exists_before_save()
            throws InterIdAlreadyExistsBeforeSaveException {
        given(interService.save(data.firstInterDto))
                .willThrow(InterIdAlreadyExistsBeforeSaveException)
        mockMvc.perform(post(INTER_BASE_URL_REST_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(data.jsonFirstInterDto))
                .andExpect(status().isUnprocessableEntity())
                .andReturn()
    }

    @Test
    @Order(13)
    @DisplayName("testPostSave_nd_and_type_unique_index_constraint_violation")
    void testPostSave_nd_and_type_unique_index_constraint_violation()
            throws InterAlreadyExistsException {
        given(interService.isUniqueKey(anyString(), anyString()))
                .willThrow(InterAlreadyExistsException)
        mockMvc.perform(post(INTER_BASE_URL_REST_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(data.jsonFirstInterDtoWithoutId))
                .andExpect(status().isConflict())
                .andReturn()
    }


    @Test
    @Order(14)
    @DisplayName("testDelete")
    void testDelete() {
        when(interService.findById(data.firstInterDto.id))
                .thenReturn(data.firstInterDto)
        doNothing().when(interService)
                .delete(data.firstInterDto.id)
        mockMvc.perform(delete("$INTER_BASE_URL_REST_API/{id}",
                data.firstInterDto.id))
                .andExpect(status().isNoContent())
                .andReturn()
        verify(interService, times(1))
                .findById(data.firstInterDto.getId())
        verify(interService, times(1))
                .delete(data.firstInterDto.getId())
        verifyNoMoreInteractions(interService)
    }


    @Test
    @Order(15)
    @DisplayName("testDelete_id_not_exists_before_delete")
    void testDelete_id_not_exists_before_delete()
            throws InterIdNotExistsBeforeDeleteException {

        given(interService.findById(data.firstInterDto.id + 1))
                .willReturn(null)
                .willThrow(InterIdNotExistsBeforeDeleteException)

        mockMvc.perform(delete(
                "${INTER_BASE_URL_REST_API}/{id}",
                data.firstInterDto.id + 1))
                .andExpect(status().isUnprocessableEntity())
                .andReturn()

    }


    @Test
    @Order(16)
    @DisplayName("testUpdateInter")
    void testUpdate() {
        given(interService.isUniqueIndexConsistent(
                anyLong(),
                anyString(),
                anyString())).willReturn(true)
        given(interService.save(data.firstInterDto))
                .willReturn(data.firstInterDto)
        mockMvc.perform(put(INTER_BASE_URL_REST_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(data.jsonFirstInterDto))
        .andExpect(status().isOk())
//        .andExpect(jsonPath('id')
//        .value(data.firstInterDto.id))
                .andDo(print())
                .andReturn()
    }

}