package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.model.InterDto
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.time.LocalDate
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Slf4j
@Service
@TypeChecked
@Transactional(readOnly = true)
class InterServiceImpl implements InterService {


    final InterDto firstInterDto = new InterDto(
            id: 1, nd: "0144639035",
            lastName: "Lalande", firstName: "Julien",
            dateTime: stringToZonedDateTimeSystemDefault(
                    "2018-10-29",
                    "10:00:00"),
            contract: "IQ", typeInter: "BAOC")
    final InterDto lastInterDto = new InterDto(
            id: 109, nd: "0143485957",
            lastName: "Bouvier", firstName: "Steven",
            dateTime: stringToZonedDateTimeSystemDefault(
                    "2019-01-04",
                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")
    final InterDto interDto = new InterDto(
            id: 104, nd: "0144820811",
            lastName: "Gustin", firstName: "Jean-Pierre",
            dateTime: stringToZonedDateTimeSystemDefault(
                    "2019-01-02",
                    "13:00:00"),
            contract: "IQ", typeInter: "BAOC")
    final InterDto prevInterDto = new InterDto(
            id: 103, nd: "0142069836",
            lastName: "Maugee", firstName: "Eric",
            dateTime: stringToZonedDateTimeSystemDefault(
                    "2018-12-31",
                    "12:00:00"),
            contract: "IQ", typeInter: "BAAP")
    final InterDto nextInterDto = new InterDto(
            id: 105, nd: "0143486423",
            lastName: "QUANTUM",
            dateTime: stringToZonedDateTimeSystemDefault(
                    "2019-01-02",
                    "10:00:00"),
            contract: "LM", typeInter: "BAAP")


    static ZonedDateTime stringToZonedDateTimeSystemDefault(String date, String time) {
        ZonedDateTime.of(
                LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss")),
                ZoneId.systemDefault())
    }
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
    void create(InterDto interDto) {

    }

    @Override
    @Transactional
    void update(InterDto interDto) {

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
