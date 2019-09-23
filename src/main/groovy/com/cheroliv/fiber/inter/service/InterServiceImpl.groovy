package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.domain.Inter

//import com.cheroliv.fiber.dao.InterRepository
//import com.cheroliv.fiber.inter.domain.Inter
//import com.cheroliv.fiber.inter.domain.InterUtils
//import groovy.json.JsonBuilder
//import groovy.json.JsonSlurper
//import groovy.transform.CompileStatic
//import groovy.transform.TypeCheckingMode
//import groovy.util.logging.Slf4j
//import org.apache.commons.io.FilenameUtils
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//
//import java.nio.charset.StandardCharsets
//import java.nio.file.Files
//import java.nio.file.LinkOption
//import java.nio.file.Path
//import java.nio.file.Paths
//import java.text.SimpleDateFormat
//import java.time.format.DateTimeFormatter
//
//@Slf4j
@Service
//@CompileStatic
class InterServiceImpl implements InterService{

 @Override
 Inter find(String nd, String type) {
  return null
 }

 @Override
 Integer countMois() {
  return null
 }

 @Override
 List<Map<String, Integer>> findAllMoisFormatFrParAnnee() {
  return null
 }

 @Override
 String getFiberJsonFilePath(String baseFolderPath) {
  return null
 }

 @Override
 void setUp() {

 }

 @Override
 void importJsonFromFile(String path) throws IOException {

 }

 @Override
 String buildJsonInter(Inter inter) {
  return null
 }

 @Override
 void saveToJsonFile(String path) throws IOException {

 }
}

//{
//    final InterRepository interRepository
//    final String fiberUserDataFolderName
//    final String jsonBackUpFileName
//
//    @Autowired
//    InterServiceImpl(@Value('${fiberUserDataFolderName}')
//                             String fiberUserDataFolderName,
//                     @Value('${jsonBackUpFileName}')
//                             String jsonBackUpFileName,
//                     InterRepository interRepository) {
//        this.interRepository = interRepository
//        this.fiberUserDataFolderName = fiberUserDataFolderName
//        this.jsonBackUpFileName = jsonBackUpFileName
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    Inter find(String nd, String type) {
//        Optional<Inter> result = interRepository.find nd, type
//        if (result.present) result.get()
//        else throw new NoSuchElementException("No value present for find($nd, $type)")
//    }
//
//
//    @Transactional(readOnly = true)
//    @Override
//    Integer countMois() {
//        interRepository.distinctMoisParAnnee()?.size() ?: 0
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    List<Map<String, Integer>> findAllMoisFormatFrParAnnee() {
//        List<List<Integer>> result = interRepository.distinctMoisParAnnee()
//        List<Map<String, Integer>> finalResult =
//                new ArrayList<Map<String, Integer>>(result.size())
//        //todo:handle if date is null on db
//        result.eachWithIndex { List<Integer> item, int idx ->
//            Integer intMois = item.get(0)
//            Integer annee = item.get(1)
//            Map<String, Integer> map = new HashMap<String, Integer>(1)
//            map[InterUtils.convertNombreEnMois(intMois)] = annee
//            finalResult.add(idx, map)
//        }
//        finalResult
//    }
//
//    String getFiberJsonFilePath(String baseFolderPath) {
//        String separator =
//                Paths.get(System.getProperty("user.home"))
//                        .fileSystem
//                        .separator
//        Paths.get("${baseFolderPath}" +
//                "$separator$fiberUserDataFolderName" +
//                "$separator" +
//                "$jsonBackUpFileName").toString()
//    }
//
//    /**
//     * créer le dossier $fiberUserDataFolderName, à la racine du l'utilisateur de l'application
//     */
//    @Override
//    void setUp() {
//        String separator = Paths.get(System.getProperty("user.home"))
//                .fileSystem.separator
//        Path path = Paths.get "${System.getProperty("user.home")}$separator" +
//                "$fiberUserDataFolderName"
//        Files.exists(path, LinkOption.NOFOLLOW_LINKS) &&
//                Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS) ?:
//                path.toFile().mkdir()
//    }
//
//    @Transactional
//    @Override
//    void importJsonFromFile(String path) throws IOException {
//        Object jsonInters = new JsonSlurper().parse(new File(path))
//        (jsonInters as List<Map<String, String>>).each { Map<String, String> it ->
//            if (!it.isEmpty()) {
//                interRepository.find(
//                        it["ND"], it["type"]) ?: interRepository
//                        .save(new Inter(nd: it["ND"],
//                                nom: it["nom"],
//                                prenom: it["prenom"],
//                                heure: InterUtils.parseStringHeureToLocalTime(it["heure"]),
//                                date: InterUtils.parseStringDateToLocalDate(it["date"]),
//                                contrat: it["contrat"],
//                                type: it["type"]))
//            }
//        }
//    }
//
//    @CompileStatic(TypeCheckingMode.SKIP)
//    @Override
//    String buildJsonInter(Inter inter) {
//        JsonBuilder builder = new JsonBuilder()
//        String result =
//                builder {
//                    '"id_inter"' "\"${inter.id}\""
//                    '"ND"' "\"${inter.nd}\""
//                    '"nom"' "\"${inter.nom}\""
//                    '"prenom"' "\"${inter.prenom}\""
//                    '"heure"' inter.heure.hour > 9 ? "\"${inter.heure.hour}:00:00\"" : "\"0${inter.heure.hour}:00:00\""
//                    '"date"' "\"${inter.date.format DateTimeFormatter.ofPattern("yyyy-MM-dd")}\""
//                    '"contrat"' "\"${inter.contrat}\""
//                    '"type"' "\"${inter.type}\""
//                }.toString()
//        "{${result.substring(1, result.size() - 1)}}".toString()
//    }
//
//
//    @CompileStatic
//    @Transactional(readOnly = true)
//    @Override
//    void saveToJsonFile(String path) throws IOException {
//        List<String> jsonList = new ArrayList<String>()
//
//        //building json
//        interRepository.findAll().each { Inter inter ->
//            jsonList.add "${buildJsonInter(inter)}\n".toString()
//        }
//
//        //renaming file
//        String ajout = new SimpleDateFormat("yyyyMMddHHmmss")
//                .format new Date()
//        new File(path).renameTo(
//                "${FilenameUtils.removeExtension path}" +
//                        "${ajout}.json")
//        //saving to file
//        File jsonBackUpFile = new File(path)
//        jsonBackUpFile.createNewFile()
//        jsonBackUpFile.setText(jsonList.toListString(), StandardCharsets.UTF_8.toString())
//    }
//}
