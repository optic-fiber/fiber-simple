package com.cheroliv.fiber


import com.cheroliv.fiber.inter.domain.InterventionEntity
import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.inter.dto.InterventionDto
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Slf4j
@Singleton
@CompileStatic
class TestData {

    static final InterventionDto firstInterDto = new InterventionDto(
            id: 1, nd: "0144639035",
            lastName: "Lalande", firstName: "Julien",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2018-10-29",
                    "10:00:00"),
            contract: "IQ", typeInter: "BAOC")

    static final InterventionDto lastInterDto = new InterventionDto(
            id: 109, nd: "0143485957",
            lastName: "Bouvier", firstName: "Steven",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2019-01-04",
                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")

    static final InterventionDto interDto = new InterventionDto(
            id: 104, nd: "0144820811",
            lastName: "Gustin", firstName: "Jean-Pierre",
            contract: "IQ", typeInter: "BAOC",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2019-01-02",
                    "13:00:00"))

    static final InterventionDto prevInterDto = new InterventionDto(
            id: 103, nd: "0142069836",
            lastName: "Maugee", firstName: "Eric",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2018-12-31",
                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")

    static final InterventionDto nextInterDto = new InterventionDto(
            id: 105, nd: "0143486423",
            lastName: "QUANTUM",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2019-01-02",
                    "10:00:00"),
            contract: "LM", typeInter: "BAAP")

    static final Integer newInterDtoId = 106

    static final InterventionDto newInterDto =
            new InterventionDto(
                    nd: "0102030405",
                    lastName: 'Doe',
                    firstName: 'John',
                    dateTime: LocalDateTime.now(),
                    contract: ContractEnum.LM.name(),
                    typeInter: TypeInterEnum.BAFA.name())

    static final InterventionDto expectedPersistedInterDto =
            new InterventionDto(
                    id: newInterDtoId,
                    nd: newInterDto.nd,
                    dateTime: newInterDto.dateTime,
                    firstName: newInterDto.firstName,
                    lastName: newInterDto.lastName,
                    typeInter: newInterDto.typeInter,
                    contract: newInterDto.contract)

    static final List<InterventionDto> interDtos = [
            firstInterDto, prevInterDto, interDto,
            nextInterDto, lastInterDto]

    static final String jsonNewtInterDto =
            '{"nd":"0102030405",' +
                    '"firstName":"John","lastName":"Doe",' +
                    '"contract":"LM","typeInter":"BAFA",' +
                    '"dateTime":"2019-09-30 17:47:44"}'

    static final String jsonFirstInterDto = '{"id":1,' +
            '"nd":"0144639035","firstName":"Julien",' +
            '"lastName":"Lalande","contract":"IQ",' +
            '"typeInter":"BAOC","dateTime":' +
            '"2018-10-29 10:00:00"}'

    static final String jsonFirstInterDtoWithoutId =
            '{"nd":"0144639035","firstName":"Julien",' +
                    '"lastName":"Lalande","contract":"IQ",' +
                    '"typeInter":"BAOC","dateTime":' +
                    '"2018-10-29 10:00:00"}'

    static final InterventionEntity firstInter = new InterventionEntity(
            id: firstInterDto.id,
            nd: firstInterDto.nd,
            firstNameClient: firstInterDto.firstName,
            lastNameClient: firstInterDto.lastName,
            dateTimeInter: firstInterDto.dateTime,
            contract: ContractEnum.valueOfName(
                    firstInterDto.contract),
            typeInter: TypeInterEnum.valueOfName(
                    firstInterDto.typeInter))

    static final InterventionEntity inter = new InterventionEntity(
            id: 104, nd: interDto.nd,
            lastNameClient: interDto.lastName,
            firstNameClient:  interDto.firstName,
            contract: ContractEnum
                    .valueOfName( interDto.contract),
            typeInter: TypeInterEnum
                    .valueOfName( interDto.typeInter),
            dateTimeInter:  interDto.dateTime)

    static final InterventionEntity prevInter = new InterventionEntity(
            id: prevInterDto.id,
            nd: prevInterDto.nd,
            firstNameClient: prevInterDto.firstName,
            lastNameClient: prevInterDto.lastName,
            contract: ContractEnum.valueOfName(
                    prevInterDto.contract),
            typeInter: TypeInterEnum.valueOfName(
                    prevInterDto.typeInter),
            dateTimeInter: prevInterDto.dateTime)

    static final InterventionEntity lastInter = new InterventionEntity(
            id: lastInterDto.id,
            nd: lastInterDto.nd,
            firstNameClient: lastInterDto.firstName,
            lastNameClient: lastInterDto.lastName,
            contract: ContractEnum.valueOfName(
                    lastInterDto.contract),
            typeInter: TypeInterEnum.valueOfName(
                    lastInterDto.typeInter),
            dateTimeInter: lastInterDto.dateTime)

    static final InterventionEntity nextInter = new InterventionEntity(
            id: nextInterDto.id,
            nd: nextInterDto.nd,
            firstNameClient: nextInterDto.firstName,
            lastNameClient: nextInterDto.lastName,
            contract: ContractEnum.valueOfName(
                    nextInterDto.contract),
            typeInter: TypeInterEnum.valueOfName(
                    nextInterDto.typeInter),
            dateTimeInter: nextInterDto.dateTime)

    static LocalDateTime stringToLocalDateTimeSystemDefault(
            String date, String time) {
        LocalDateTime.of(
                LocalDate.parse(date, DateTimeFormatter
                        .ofPattern("yyyy-MM-dd")),
                LocalTime.parse(time, DateTimeFormatter
                        .ofPattern("HH:mm:ss")))
    }
}
