package com.cheroliv.fiber.console.domain

//import groovy.json.JsonSlurper
//import groovy.transform.CompileStatic
//import groovy.util.logging.Slf4j
//import org.junit.jupiter.api.MethodOrderer
//import org.junit.jupiter.api.Order
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.TestMethodOrder
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.context.ApplicationContext
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.junit.jupiter.SpringExtension
//
//import javax.validation.ConstraintViolation
//import javax.validation.Validator
//import java.time.LocalDate
//import java.time.LocalTime
//
//import static com.cheroliv.fiber.domain.InterUtils.*
//import static com.xlson.groovycsv.CsvParser.parseCsv
//import static java.lang.Long.parseLong
//
//@Slf4j
//@CompileStatic
//@SpringBootTest
//@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@TestMethodOrder(MethodOrderer.OrderAnnotation)
class InterTest {

//    static Integer interFieldMapSize = 7
//
//
//    @Autowired
//    ApplicationContext applicationContext
//    @Autowired
//    Validator validator
//
//    //Recupere les données du fichier CSV dans un iterator
//    Iterator getCsvData() {
//        parseCsv applicationContext
//                .getResource("classpath:inter.csv")
//                .file
//                .getText('utf-8'),
//                separator: ',',
//                readFirstLine: false
//    }
//
//    List<Map<String, String>> getJsonData() {
//        new JsonSlurper().parseText(applicationContext
//                .getResource("classpath:inter.json")
//                .file
//                .getText('utf-8')) as List<Map<String, String>>
//    }
//
//    @Test
//    @Order(1)
//    void testTimeStringToInteger() {
//        Integer[] heures =
//                [9, 13, 8, 9, 8,
//                 12, 12, 8, 13, 15]
//        String[] strHeures = [
//                "09:00:00", "13:00:00",
//                "08:00:00", "09:00:00",
//                "08:00:00", "12:00:00",
//                "12:00:00", "08:00:00",
//                "13:00:00", "15:00:00"]
//        strHeures.eachWithIndex { it, idx ->
//            assert timeStringToInteger(it) == heures[idx]
//        }
//    }
//
//
//    @Test
//    @Order(2)
//    void testToArrayString_ArraySize() {
//        csvData.each {
//            Inter interCsv = new Inter(
//                    id: parseLong(it[INTER_ID_COLUMN_NAME] as String),
//                    nd: it[INTER_ND_COLUMN_NAME] as String,
//                    nom: it[INTER_NOM_COLUMN_NAME] as String,
//                    prenom: it[INTER_PRENOM_COLUMN_NAME] as String,
//                    heure: parseStringHeureToLocalTime(it[INTER_HEURE_COLUMN_NAME] as String),
//                    date: LocalDate.parse(it[INTER_DATE_COLUMN_NAME] as String),
//                    contrat: it[INTER_CONTRAT_COLUMN_NAME] as String,
//                    type: it[INTER_TYPE_COLUMN_NAME] as String)
//            //taille du tableau est de la taille 7
//            // cad les champs de Inter sans l'id
//            assert interFieldMapSize == interCsv.toArrayString().size()
//        }
//    }
//
//
//    @Test
//    @Order(3)
//    void testNdNotNullConstraint() {
//        Inter inter = new Inter()
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "nd"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate ==
//                NOT_NULL_CSTRT_TEMPLATE_MSG
//
//        inter.nd = "0101010101"
//        constraintViolations =
//                validator.validateProperty inter, "nd"
//        assert constraintViolations.size() == 0
//    }
//
//
//    @Test
//    @Order(4)
//    void testNdSizeConstraint() {
//        Inter inter = new Inter(nd: "101010101")
//        assert inter.nd.size() != 10
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "nd"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate ==
//                SIZE_CSTRT_TEMPLATE_MSG
//
//        inter.nd = "0101010101"
//        assert inter.nd.size() == 10
//        constraintViolations =
//                validator.validateProperty inter, "nd"
//        assert constraintViolations.size() == 0
//    }
//
//
//    @Test
//    @Order(5)
//    void testTypeNotNullConstraint() {
//        Inter inter = new Inter()
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "type"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate ==
//                NOT_NULL_CSTRT_TEMPLATE_MSG
//
//        inter.type = "BAAP"
//        constraintViolations =
//                validator.validateProperty inter, "type"
//        assert constraintViolations.size() == 0
//    }
//
//    @Test
//    @Order(6)
//    void testTypePatternConstraint() {
//        Inter inter = new Inter(type: "foo")
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "type"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate ==
//                PATTERN_CSTRT_TEMPLATE_MSG
//
//        inter.type = "BAAP"
//        constraintViolations =
//                validator.validateProperty inter, "type"
//        assert constraintViolations.size() == 0
//        inter.type = "BAOC"
//        constraintViolations =
//                validator.validateProperty inter, "type"
//        assert constraintViolations.size() == 0
//        inter.type = "BAFA"
//        constraintViolations =
//                validator.validateProperty inter, "type"
//        assert constraintViolations.size() == 0
//        inter.type = "BAST"
//        constraintViolations =
//                validator.validateProperty inter, "type"
//        assert constraintViolations.size() == 0
//        inter.type = "PLP"
//        constraintViolations =
//                validator.validateProperty inter, "type"
//        assert constraintViolations.size() == 0
//    }
//
//    @Test
//    @Order(7)
//    void testContratNotNullConstraint() {
//        Inter inter = new Inter()
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "contrat"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate,
//                NOT_NULL_CSTRT_TEMPLATE_MSG
//
//        inter.contrat = "LM"
//        constraintViolations =
//                validator.validateProperty inter, "contrat"
//        assert constraintViolations.size() == 0
//    }
//
//    @Test
//    @Order(8)
//    void testContratPatternConstraint() {
//        Inter inter = new Inter(contrat: "foo")
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "contrat"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate ==
//                PATTERN_CSTRT_TEMPLATE_MSG
//
//        inter.contrat = "LM"
//        constraintViolations =
//                validator.validateProperty inter, "contrat"
//        assert constraintViolations.size() == 0
//        inter.contrat = "IQ"
//        constraintViolations =
//                validator.validateProperty inter, "contrat"
//        assert constraintViolations.size() == 0
//        inter.contrat = "Passage de cable"
//        constraintViolations =
//                validator.validateProperty inter, "contrat"
//        assert constraintViolations.size() == 0
//    }
//
//    @Test
//    @Order(9)
//    void testHeureNotNullConstraint() {
//        Inter inter = new Inter()
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "heure"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate ==
//                NOT_NULL_CSTRT_TEMPLATE_MSG
//
//        inter.heure = LocalTime.of(9, 0, 0)
//        constraintViolations =
//                validator.validateProperty inter, "heure"
//        assert constraintViolations.size() == 0
//    }
//
//    @Test
//    @Order(10)
//    void testDateNotNullConstraint() {
//        Inter inter = new Inter()
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "date"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate ==
//                NOT_NULL_CSTRT_TEMPLATE_MSG
//
//        inter.date = LocalDate.now()
//        constraintViolations =
//                validator.validateProperty inter, "date"
//        assert constraintViolations.size() == 0
//    }
//
//    @Test
//    @Order(11)
//    void testPrenomSizeConstraint() {
//        String prenom = ""
//        for (int i = 0; prenom.size() <= PRENOM_SIZE_VALUE; i++) {
//            prenom = prenom + i.toString()
//        }
//        assert !(prenom.size() <= PRENOM_SIZE_VALUE)
//        Inter inter = new Inter(prenom: prenom)
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "prenom"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate ==
//                SIZE_CSTRT_TEMPLATE_MSG
//
//        inter.prenom = "John"
//        constraintViolations =
//                validator.validateProperty inter, "prenom"
//        assert constraintViolations.size() == 0
//    }
//
//    @Test
//    @Order(12)
//    void testNomSizeConstraint() {
//        String nom = ""
//        for (int i = 0; nom.size() <= NOM_SIZE_VALUE; i++) {
//            nom = nom + i.toString()
//        }
//        assert !(nom.size() <= NOM_SIZE_VALUE)
//        Inter inter = new Inter(nom: nom)
//        Set<ConstraintViolation<Inter>> constraintViolations =
//                validator.validateProperty inter, "nom"
//        assert constraintViolations
//                .iterator()
//                .next()
//                .messageTemplate ==
//                SIZE_CSTRT_TEMPLATE_MSG
//
//        inter.nom = "Doe"
//        constraintViolations =
//                validator.validateProperty inter, "nom"
//        assert constraintViolations.size() == 0
//    }
}
