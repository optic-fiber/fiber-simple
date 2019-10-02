package com.cheroliv.fiber

import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.inter.model.InterDto
import groovy.transform.CompileStatic

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Singleton
@CompileStatic
class TestData {

    static final String jsonNewtInterDto = '{"nd":"0102030405",' +
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


    static final InterDto firstInterDto = new InterDto(
            id: 1, nd: "0144639035",
            lastName: "Lalande", firstName: "Julien",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2018-10-29",
                    "10:00:00"),
            contract: "IQ", typeInter: "BAOC")
    static final InterDto lastInterDto = new InterDto(
            id: 109, nd: "0143485957",
            lastName: "Bouvier", firstName: "Steven",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2019-01-04",
                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")
    static final InterDto interDto = new InterDto(
            id: 104, nd: "0144820811",
            lastName: "Gustin", firstName: "Jean-Pierre",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2019-01-02",
                    "13:00:00"),
            contract: "IQ", typeInter: "BAOC")
    static final InterDto prevInterDto = new InterDto(
            id: 103, nd: "0142069836",
            lastName: "Maugee", firstName: "Eric",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2018-12-31",
                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")
    static final InterDto nextInterDto = new InterDto(
            id: 105, nd: "0143486423",
            lastName: "QUANTUM",
            dateTime: stringToLocalDateTimeSystemDefault(
                    "2019-01-02",
                    "10:00:00"),
            contract: "LM", typeInter: "BAAP")
    static final Integer newInterDtoId = 106
    static final InterDto newInterDto = new InterDto(
            nd: "0102030405",
            lastName: 'Doe',
            firstName: 'John',
            dateTime: LocalDateTime.now(),
            contract: ContractEnum.LM.name(),
            typeInter: TypeInterEnum.BAFA.name()
    )
    static final InterDto expectedPersistedInterDto = new InterDto(
            id: newInterDtoId,
            nd: newInterDto.nd,
            dateTime: newInterDto.dateTime,
            firstName: newInterDto.firstName,
            lastName: newInterDto.lastName,
            typeInter: newInterDto.typeInter,
            contract: newInterDto.contract)

    static final List<InterDto> inters = [firstInterDto, prevInterDto, interDto, nextInterDto, lastInterDto]

    static LocalDateTime stringToLocalDateTimeSystemDefault(String date, String time) {
        LocalDateTime.of(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss")))
    }
}
