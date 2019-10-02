package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.TestData
import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
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
@DisplayName('InterServiceImplTest')
@ExtendWith(MockitoExtension)
@WebMvcTest(InterService)
class InterServiceImplTest {
    @BeforeAll
    static void init() {
        MockitoAnnotations.initMocks(this)
    }
    TestData data = TestData.getInstance()

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
        Long id = data.firstInterDto.id
        Inter expectedRepoResult = new Inter(
                id: data.firstInterDto.id,
                nd: data.firstInterDto.nd,
                firstNameClient: data.firstInterDto.firstName,
                lastNameClient: data.firstInterDto.lastName,
                dateTimeInter: data.firstInterDto.dateTime,
                contract: ContractEnum.valueOfName(data.firstInterDto.contract),
                typeInter: TypeInterEnum.valueOfName(data.firstInterDto.typeInter))

        given(repo.findById(id))
                .willReturn(Optional
                        .ofNullable(expectedRepoResult))

        assert reflectionEquals(interService.get(id),
                data.firstInterDto)

        assert interService.get(id).id == data.firstInterDto.id
        assert interService.get(id).nd == data.firstInterDto.nd
        assert interService.get(id).firstName == data.firstInterDto.firstName
        assert interService.get(id).lastName == data.firstInterDto.lastName
        assert interService.get(id).dateTime == data.firstInterDto.dateTime
        assert interService.get(id).contract == data.firstInterDto.contract
        assert interService.get(id).typeInter == data.firstInterDto.typeInter


    }

    @Test
    @Order(4)
    @Disabled
    @DisplayName('testGetFirst')
    void testGetFirst() {
        assert reflectionEquals(this.interService.getFirst(),
                data.firstInterDto)
    }
//
//    void testGetPrevious() {
//    }
//
//    void testSave() {
//    }
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
