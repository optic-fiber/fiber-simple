package com.cheroliv.fiber

import com.cheroliv.fiber.inter.model.InterDto
import com.fasterxml.jackson.databind.ObjectMapper
import groovy.json.JsonSlurper
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j

import java.nio.charset.StandardCharsets

@Slf4j
@TypeChecked
class CheckDataValues {
//    @Test@Disabled
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

        log.info toJson(TestData.instance.newInterDto)


    }

    static String toJson(InterDto interDto) {
        new ObjectMapper().writeValueAsString(interDto)
    }
}





