package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.TestData
import com.cheroliv.fiber.inter.repository.InterRepository
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.*
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.MockitoAnnotations
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean

import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals
import static org.mockito.BDDMockito.given

@Slf4j
@TypeChecked
@TestMethodOrder(OrderAnnotation)
@DisplayName('InterServiceImplUnitTest')
@ExtendWith(MockitoExtension)
@WebMvcTest(InterService)
class InterServiceImplUnitTest {
    @BeforeAll
    static void init() {
        MockitoAnnotations.initMocks(this)
    }
    TestData data = TestData.instance

    @MockBean
    InterDataService dataService
    @MockBean
    InterRepository repo
    @InjectMocks
    InterService interService = new InterServiceImpl()


    @Test
    @Order(1)
    @DisplayName('testGet_id_null')
    void testGet_id_null() {
        assert interService.get(null) == null
    }

    @Test
    @Order(2)
    @DisplayName('testGet_id_not_null_inter_not_exists')
    void testGet_id_not_null_inter_not_exists() {
        Long id = 3L
        given(repo.findById(id)).willReturn(Optional.empty())
        assert interService.get(id) == null
    }


    @Test
    @Order(3)
    @DisplayName('testGet_inter_exists')
    void testGet_inter_exists() {
        given(repo.findById(data.firstInterDto.id))
                .willReturn(Optional
                        .ofNullable(data.firstInter))
        assert reflectionEquals(
                interService.get(data.firstInterDto.id),
                data.firstInterDto)
    }

    @Test
    @Order(4)
    @DisplayName('testGetFirst_return_null')
    void testGetFirst_return_null() {
        given(repo.findByDateTimeInterMin())
                .willReturn([])
        assert !interService.first
    }


    @Test
    @Order(5)
    @DisplayName('testGetFirst')
    void testGetFirst() {
        given(repo.findByDateTimeInterMin())
                .willReturn([Optional.of(data.firstInter)])
        assert reflectionEquals(interService.first,
                data.firstInterDto)
    }

    @Test
    @Order(6)
    @DisplayName('testGetPrevious_with_id_null')
    void testGetPrevious_with_id_null() {
        given(repo.findById(null))
                .willReturn(Optional.empty())
        assert !interService.getPrevious(null)
    }

    @Test
    @Order(6)
    @DisplayName('testGetPrevious_with_id_first')
    void testGetPrevious_with_id_first() {
        given(repo.findById(data.firstInter.id))
                .willReturn(Optional.of(data.firstInter))
        given(repo.findByDateTimeInterMin())
                .willReturn([Optional.of(data.firstInter)])
        assert reflectionEquals(data.firstInterDto,
                interService.getPrevious(data.firstInter.id))
    }

    @Test
    @Order(7)
    @DisplayName('testGetPrevious')
    void testGetPrevious() {
        given(repo.findById(data.interDto.id))
                .willReturn(Optional.of(data.inter))
        given(repo.findByDateTimeInterMin())
                .willReturn([Optional.of(data.prevInter)])
        given(repo.previous(data.interDto.id))
                .willReturn(Optional.of(data.prevInter))
        assert reflectionEquals(
                interService.getPrevious(data.inter.id),
                data.prevInterDto)
    }

    @Test
    @Order(8)
    @DisplayName('testSave')
    void testSave() {
    }
//
//    void testGetNext() {
//    }
//
//    void testGetLast() {
//    }
//
//    void testIsUniqueIndexAvailable() {
//    }
//
//    void testDelete() {
//    }
//
//    void testFindById() {
//    }
//
//    void testIsUniqueIndexConsistent() {
//    }
//
//    void testSaveWithPatch() {
//    }
}
