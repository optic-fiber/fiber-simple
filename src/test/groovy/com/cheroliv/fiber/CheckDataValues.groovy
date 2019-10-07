package com.cheroliv.fiber

import com.cheroliv.fiber.inter.domain.InterUtils
import com.cheroliv.fiber.inter.dto.InterDto
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import groovy.transform.CompileStatic
import groovy.util.logging.Slf4j
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

import java.nio.charset.StandardCharsets
import java.time.LocalDateTime

@Slf4j
@CompileStatic
class CheckDataValues {


//    //the data  file
    String dataSetFileRelativePath =
            'src/test/resources/inters_full.json'

    // the data file content
    String dataSetText = getFileTextOnproject(dataSetFileRelativePath)

    // Slurp(parse) the content
    List<Map<String, String>> dataListMap =
            (new JsonSlurper().parseText(dataSetText)
                    as List<Map<String, String>>)

    // build a list of localdates
    List<LocalDateTime> dates = dataListMap.collect {
        Map<String, String> it ->
            buildDateTime(it.get('date'), it.get('heure'))
    }

    // build a map with index as key and date as value
    Map<Integer, LocalDateTime> datesIndexedMap = dates
            .withIndex()
            .collectEntries() {
                LocalDateTime it, Integer idx ->
                    [(idx): it]
            }

    // group by dates not disctinct, with date as key and map as value
    // this map is the index of date from the original content,
    // and the value is the date
    Map<LocalDateTime, Map<Integer, LocalDateTime>> duplicateDatesMap =
            datesIndexedMap.groupBy {
                Map.Entry<Integer, LocalDateTime> it ->
                    it.value
            }.findAll {
                Map.Entry<LocalDateTime, Map<Integer, LocalDateTime>> it ->
                    it.value.size() > 1
            }

    List<Set<Integer>> duplicateDatesGroupedByIndex =
            duplicateDatesMap.collect {
                Map.Entry<LocalDateTime, Map<Integer, LocalDateTime>> it ->
                    it.value.keySet()
            }

    @Test
    @Disabled
    void testJsonValues() {
        File file = new File(
                new File(".").canonicalPath +
                        System.getProperty("file.separator") +
                        "src" +
                        System.getProperty("file.separator") +
                        "main" +
                        System.getProperty("file.separator") +
                        "resources" +
                        System.getProperty("file.separator") +
                        "inters.json")
        assert file.exists() && file.isFile() && !file.isDirectory()
        String jsonInters = file.getText(StandardCharsets.UTF_8.name())
        Object datas = new JsonSlurper().parseText(jsonInters)
        (datas as List<Map<String, String>>).each {
//            println it["contrat"]
        }

//        log.info toJson(TestData.instance.newInterDto)


    }

    String toJson(InterDto interDto) {
        new ObjectMapper().writeValueAsString(interDto)
    }

    List<Map<LocalDateTime, Map<String, Object>>> sameDates = new ArrayList<>()

    String getFileTextOnproject(String relativePath) {
        File file = new File(
                new File(".").canonicalPath +
                        System.getProperty("file.separator") +
                        relativePath)
        assert file.exists() && file.isFile() && !file.isDirectory()
        file.getText(StandardCharsets.UTF_8.name())
    }


    LocalDateTime buildDateTime(String strDate, String strHour) {
        LocalDateTime.of(
                InterUtils.parseStringDateToLocalDate(strDate),
                InterUtils.parseStringHeureToLocalTime(strHour))
    }

    @Test
    @Disabled
    @DisplayName("createNonDistinctDatesCollection")
    void createNonDistinctDatesCollection() {
        duplicateDatesMap.each { Map.Entry<LocalDateTime, Map<Integer, LocalDateTime>> that ->
            println that
        }
    }

    Boolean isTimeExistsInDataset(LocalDateTime dateTime) {
        dates.contains(dateTime)
    }

    Boolean isMinusMoreThen8ExistsInDataset(LocalDateTime dateTime) {
        def minus = dateTime.minusHours(1)
        if (minus.getHour() > 8)
            dates.contains(dateTime.minusHours(1))
        false
    }

    Boolean isPlusLessThan19ExistsInDataset(LocalDateTime dateTime) {
        def plus = dateTime.plusHours(1)
        if (plus.getHour() < 19)
            dates.contains(dateTime.plusHours(1))
        false


    }

    void makeDateUnique(int index) {
        //TODO finish it up
//        println index
//        println isMinusMoreThen8ExistsInDataset(dates.get(index))
//        println isPlusLessThan19ExistsInDataset(dates.get(index))

        Boolean result = false
        while (isTimeExistsInDataset(dates.get(index)) &&
                dates.get(index).getHour() > 8) {
            LocalDateTime uniqueDateTime
            if (isTimeExistsInDataset(dates.get(index))) {
                if (dates.get(index).getHour() == 8) {

                }
                dates.set(index, uniqueDateTime)
                result = true
                break
            }
        }

        if (!result) throw new RuntimeException(index.toString())
//        println result
    }
    @Test
    @DisplayName('addOneHourToLocalDateTime')
    void addOneHourToLocalDateTime() {
        assert duplicateDatesGroupedByIndex.empty
        duplicateDatesGroupedByIndex.each {
            println it.toString()
        }
    }
}