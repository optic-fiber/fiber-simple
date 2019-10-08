package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.TestData
import com.cheroliv.fiber.inter.dao.InterDao
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
    InterDao dao
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
        given(dao.findById(id)).willReturn(Optional.empty())
        assert interService.get(id) == null
    }


    @Test
    @Order(3)
    @DisplayName('testGet_inter_exists')
    void testGet_inter_exists() {
        given(dao.findById(data.firstInterDto.id))
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
        given(dao.findByMinDateTimeInter())
                .willReturn([])
        assert !interService.first
    }


    @Test
    @Order(5)
    @DisplayName('testGetFirst')
    void testGetFirst() {
        given(dao.findByMinDateTimeInter())
                .willReturn([Optional.of(data.firstInter)])
        assert reflectionEquals(interService.first,
                data.firstInterDto)
    }

    @Test
    @Order(6)
    @DisplayName('testGetLast_return_null')
    void testGetLast_return_null() {
        given(dao.findByMaxDateTimeInter())
                .willReturn([])
        assert !interService.last
    }

    @Test
    @Order(7)
    @DisplayName('testGetLast')
    void testGetLast() {
        given(dao.findByMaxDateTimeInter())
                .willReturn([Optional.of(data.lastInter)])
        assert reflectionEquals(interService.last,
                data.lastInterDto)
    }

    @Test
    @Order(8)
    @DisplayName('testGetPrevious_with_id_null')
    void testGetPrevious_with_id_null() {
        given(dao.findById(null))
                .willReturn(Optional.empty())
        assert !interService.getPrevious(null)
    }

    @Test
    @Order(9)
    @DisplayName('testGetPrevious_with_id_first')
    void testGetPrevious_with_id_first() {
        given(dao.findById(data.firstInter.id))
                .willReturn(Optional.of(data.firstInter))
        given(dao.findByMinDateTimeInter())
                .willReturn([Optional.of(data.firstInter)])
        assert reflectionEquals(data.firstInterDto,
                interService.getPrevious(data.firstInter.id))
    }

    @Test
    @Order(10)
    @DisplayName('testGetPrevious')
    void testGetPrevious() {
        given(dao.findById(data.interDto.id))
                .willReturn(Optional.of(data.inter))
        given(dao.findByMinDateTimeInter())
                .willReturn([Optional.of(data.prevInter)])
        given(dao.previous(data.interDto.id))
                .willReturn(Optional.of(data.prevInter))
        assert reflectionEquals(
                interService.getPrevious(data.inter.id),
                data.prevInterDto)
    }

    @Test
    @Order(11)
    @DisplayName('testGetNext_with_id_null')
    void testGetNext_with_id_null() {
        given(dao.findById(null))
                .willReturn(Optional.empty())
        assert !interService.getNext(null)
    }

    @Test
    @Order(12)
    @DisplayName('testGetNext_with_id_last')
    void testGetNext_with_id_last() {
        given(dao.findById(data.inter.id))
                .willReturn(Optional.of(data.inter))
        given(dao.findByMaxDateTimeInter())
                .willReturn([Optional.of(data.nextInter)])
        given(dao.next(data.inter.id))
                .willReturn(Optional.of(data.nextInter))
        assert reflectionEquals(data.nextInterDto,
                interService.getNext(data.interDto.id))
    }

    @Test
    @Order(13)
    @DisplayName('test_findById_id_is_null')
    void test_findById_id_is_null() {
        assert !interService.findById(null)
    }


    @Test
    @Order(14)
    @DisplayName('test_findById_id_is_max_than_count')
    void test_findById_id_is_max_than_count() {
        assert !interService.findById(150_000_000_000)
    }


    @Test
    @Order(15)
    @DisplayName('test_findById')
    void test_findById() {
        given(dao.findById(data.inter.id))
                .willReturn(Optional.of(data.inter))
        assert reflectionEquals(data.interDto,
                interService.findById(data.inter.id))
    }


    @Test
    @Order(16)
    @DisplayName('testIsUniqueIndexConsistent')
    void testIsUniqueIndexConsistent_with_param_null() {
        assert !interService.isUniqueIndexConsistent(
                null, null, null)
    }


    @Test
    @Order(16)
    @DisplayName('testIsUniqueIndexConsistent')
    void testIsUniqueIndexConsistent_with_id_over_records() {
        Long id = 150_000_000_000_000L
        given(dao.findById(id)).willReturn(Optional.empty())
        assert !interService.isUniqueIndexConsistent(
                150_000_000_000_000L,
                data.inter.nd,
                data.inter.typeInter.name())
    }

    @Test
    @Order(17)
    @DisplayName('testIsUniqueNdIndexConsistent_with_existing_index')
    void testIsUniqueNdIndexConsistent_with_existing_index() {
        assert !interService.isUniqueIndexConsistent(
                data.firstInter.id,
                data.firstInter.nd,
                data.firstInter.typeInter.name())
    }


    @Test
    @Order(18)
    @DisplayName('testIsUniqueIndexConsistent')
    void testIsUniqueNdIndexConsistent_with_nd_type_not_already_exists() {
        given(dao.findById(data.firstInter.id))
                .willReturn(Optional.of(data.firstInter))
        given(dao.find(data.firstInter.nd, data.firstInter.typeInter))
                .willReturn(Optional.of(data.firstInter))
        assert interService.isUniqueIndexConsistent(
                data.firstInter.id,
                '0123456789',
                data.firstInter.typeInter.name())
    }


    @Test
    @Order(19)
    @DisplayName('testIsUniqueIndexAvailable_not_available')
    void testIsUniqueIndexAvailable_not_available_args_null() {
        assert !interService.isUniqueIndexAvailable(
                null, null)
    }

    @Test
    @Order(20)
    @DisplayName('testIsUniqueIndexAvailable_not_available')
    void testIsUniqueIndexAvailable_args_nd_type_not_available() {
        given(dao.find(
                data.firstInter.nd, data.firstInter.typeInter))
                .willReturn(Optional.of(data.firstInter))
        assert !interService.isUniqueIndexAvailable(
                data.firstInterDto.nd,
                data.firstInterDto.typeInter)
    }


    @Test
    @Order(21)
    @DisplayName('testIsUniqueIndexAvailable_args_nd_type_available')
    void testIsUniqueIndexAvailable_args_nd_type_available() {
        String nd = '0123456789'
        given(dao.find(
                nd, data.firstInter.typeInter))
                .willReturn(Optional.empty())
        assert interService.isUniqueIndexAvailable(
                nd,
                data.firstInterDto.typeInter)
    }

//    void testSave() {
//    }
//    void testDelete() {
//    }
//    void testSaveWithPatch() {
//    }
}
