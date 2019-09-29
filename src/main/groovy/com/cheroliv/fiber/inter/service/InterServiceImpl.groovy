package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.enumeration.InterContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.InterTypeEnum
import com.cheroliv.fiber.inter.model.InterDto
import com.cheroliv.fiber.inter.repository.InterRepository
import com.cheroliv.fiber.inter.service.exceptions.InvalidInterDtoException
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.validation.Validator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Slf4j
@Service
@TypeChecked
@Transactional(readOnly = true)
class InterServiceImpl implements InterService {


    final InterDto firstInterDto = new InterDto(
            id: 1, nd: "0144639035",
            lastName: "Lalande", firstName: "Julien",
//            dateTime: stringToLocalDateTime(
//                    "2018-10-29",
//                    "10:00:00"),
            contract: "IQ", typeInter: "BAOC")
    final InterDto lastInterDto = new InterDto(
            id: 109, nd: "0143485957",
            lastName: "Bouvier", firstName: "Steven",
//            dateTime: stringToLocalDateTime(
//                    "2019-01-04",
//                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")
    final InterDto interDto = new InterDto(
            id: 104, nd: "0144820811",
            lastName: "Gustin", firstName: "Jean-Pierre",
//            dateTime: stringToLocalDateTime(
//                    "2019-01-02",
//                    "13:00:00"),
            contract: "IQ", typeInter: "BAOC")
    final InterDto prevInterDto = new InterDto(
            id: 103, nd: "0142069836",
            lastName: "Maugee", firstName: "Eric",
//            dateTime: stringToLocalDateTime(
//                    "2018-12-31",
//                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")
    final InterDto nextInterDto = new InterDto(
            id: 105, nd: "0143486423",
            lastName: "QUANTUM",
//            dateTime: stringToLocalDateTime(
//                    "2019-01-02",
//                    "10:00:00"),
            contract: "LM", typeInter: "BAAP")

    static final InterDto newInterDto = new InterDto(
            nd: "0102030405",
            lastName: 'Doe',
            firstName: 'John',
//            dateTime: LocalDateTime.now(),
            contract: InterContractEnum.LM.name(),
            typeInter: InterTypeEnum.BAFA.name()
    )

    static LocalDateTime stringToLocalDateTime(String date, String time) {
        LocalDateTime.of(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss")))
    }


    @Autowired
    InterRepository interRepository
    @Autowired
    Validator validator

    @Override
    InterDto get(Long id) {
        this.interDto
    }

    @Override
    InterDto getFirst() {
        this.firstInterDto
    }


    @Override
    InterDto getPrevious(Long id) {
        this.prevInterDto
    }

    @Override
    @Transactional
    InterDto create(InterDto interDto) {
        def cv = validator.validate(InterDto)
        if (cv.isEmpty()) {
            Inter result = this.interRepository.save(
                    new Inter(nd: interDto.nd,
//                            dateTimeInter: interDto.dateTime,
                            firstNameClient: interDto.firstName,
                            lastNameClient: interDto.lastName,
                            typeInter: InterTypeEnum.valueOfName(interDto.typeInter),
                            contract: InterContractEnum.valueOfName(interDto.contract)))
            new InterDto(id: result.id,
                    nd: result.nd,
//                    dateTime: result.dateTimeInter,
                    firstName: result.firstNameClient,
                    lastName: result.lastNameClient,
                    typeInter: result.typeInter.name(),
                    contract: result.contract.name())
        } else throw new InvalidInterDtoException()
    }

    @Override
    @Transactional
    InterDto update(InterDto interDto) {
        new InterDto(id: 106,
                nd: newInterDto.nd,
                firstName: newInterDto.firstName,
                lastName: newInterDto.lastName,
//                dateTime: newInterDto.dateTime,
                contract: newInterDto.contract,
                typeInter: newInterDto.typeInter)
    }

    @Override
    InterDto getNext(Long id) {
        this.nextInterDto
    }

    @Override
    InterDto getLast() {
        this.lastInterDto
    }
}
