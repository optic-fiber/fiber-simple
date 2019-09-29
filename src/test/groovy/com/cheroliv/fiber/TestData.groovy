package com.cheroliv.fiber

import com.cheroliv.fiber.inter.domain.enumeration.InterContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.InterTypeEnum
import com.cheroliv.fiber.inter.model.InterDto
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.transform.CompileStatic

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@Singleton
@CompileStatic
class TestData {


    static final InterDto firstInterDto = new InterDto(
            id: 1, nd: "0144639035",
            lastName: "Lalande", firstName: "Julien",
//            dateTime: stringToLocalDateTimeSystemDefault(
//                    "2018-10-29",
//                    "10:00:00"),
            contract: "IQ", typeInter: "BAOC")
    static final InterDto lastInterDto = new InterDto(
            id: 109, nd: "0143485957",
            lastName: "Bouvier", firstName: "Steven",
//            dateTime: stringToLocalDateTimeSystemDefault(
//                    "2019-01-04",
//                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")
    static final InterDto interDto = new InterDto(
            id: 104, nd: "0144820811",
            lastName: "Gustin", firstName: "Jean-Pierre",
//            dateTime: stringToLocalDateTimeSystemDefault(
//                    "2019-01-02",
//                    "13:00:00"),
            contract: "IQ", typeInter: "BAOC")
    static final InterDto prevInterDto = new InterDto(
            id: 103, nd: "0142069836",
            lastName: "Maugee", firstName: "Eric",
//            dateTime: stringToLocalDateTimeSystemDefault(
//                    "2018-12-31",
//                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")
    static final InterDto nextInterDto = new InterDto(
            id: 105, nd: "0143486423",
            lastName: "QUANTUM",
//            dateTime: stringToLocalDateTimeSystemDefault(
//                    "2019-01-02",
//                    "10:00:00"),
            contract: "LM", typeInter: "BAAP")
    static final Integer newInterDtoId = 106
    static final InterDto newInterDto = new InterDto(
            nd: "0102030405",
            lastName: 'Doe',
            firstName: 'John',
//            dateTime: LocalDateTime.now(),
            contract: InterContractEnum.LM.name(),
            typeInter: InterTypeEnum.BAFA.name()
    )


    static final List<InterDto> inters = [firstInterDto, prevInterDto, interDto, nextInterDto, lastInterDto]

    static String jsonFromBean(Object object) {
        new ObjectMapper().writeValueAsString(object)
    }

    static LocalDateTime stringToLocalDateTimeSystemDefault(String date, String time) {
        LocalDateTime.of(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss")))
    }
}
