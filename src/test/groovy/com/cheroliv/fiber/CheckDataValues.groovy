package com.cheroliv.fiber

import groovy.json.JsonSlurper
import groovy.transform.TypeChecked
import org.junit.jupiter.api.Test

import java.nio.charset.StandardCharsets

@TypeChecked
class CheckDataValues {
    @Test
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
        (datas as List<Map<String,String>>).each {
            println it["contrat"]
        }
    }
}
