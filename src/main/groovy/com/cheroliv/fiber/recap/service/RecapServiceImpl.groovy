package com.cheroliv.fiber.recap.service

//import com.cheroliv.fiber.inter.dao.InterDao
//import com.cheroliv.fiber.inter.domain.InterUtils
//import com.cheroliv.fiber.inter.domain.InterventionEntity
//import com.cheroliv.fiber.inter.dto.InterventionDto
//import com.cheroliv.fiber.inter.service.InterDataService
//import com.cheroliv.fiber.recap.model.Recap
//import com.cheroliv.fiber.recap.spreadsheet.SpreadsheetRecap
//import groovy.transform.CompileStatic
//import groovy.util.logging.Slf4j
//import org.springframework.beans.factory.annotation.Value
//import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//
//import javax.validation.constraints.NotEmpty
//import javax.validation.constraints.NotNull
//import java.nio.file.Paths
//
//@Slf4j
//@Service
//@CompileStatic
class RecapServiceImpl implements RecapService {

//    final String classeurPathName
//    final String fiberUserDataFolderName
//    final String classeurDirectoryName
//    final InterDataService service
//    final InterDao dao
//    SpreadsheetRecap classeur
//    @NotNull
//    @NotEmpty
//    String path
//
//    @Override
//    void setPath(String path) {
//        this.path = path
//    }
//
//
//    /*
//    @Value('${application.data.home-directory-name}')
//    String homeDirectoryName
//     */
//    RecapServiceImpl(
//            @Value('${application.data.spreadsheet-file-name}')
//                    String recapitulatifSpreadsheetFileName,
//            @Value('${application.data.home-directory-name}')
//                    String homeDirectoryName,
//            @Value('${application.data.home-spreadsheet-directory-name}')
//                    String recapitulatifSpreadsheetDirectoryName,
//            InterDataService service,
//            InterDao dao) {
//        this.dao = dao
//        this.service = service
//        this.classeurPathName = recapitulatifSpreadsheetFileName
//        this.fiberUserDataFolderName = homeDirectoryName
//        this.classeurDirectoryName = recapitulatifSpreadsheetDirectoryName
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    List<String> nomFeuilles() {
//        List<Map<String, Integer>> list = service.findAllMoisFormatFrParAnnee()
//        List<String> finalList = new ArrayList()
//        list.eachWithIndex { item, idx ->
//            String key = (item as Map<String, Integer>).keySet().first()
//            String value = (item as Map<String, Integer>).get(key)
//            String monthYearLabel = "$key $value"
//            finalList.add idx, monthYearLabel
//        }
//        finalList
//    }
//
//
//    @Override
//    @Transactional(readOnly = true)
//    SpreadsheetRecap init() {
//        String strRecapPath = path +//arg
//                separator +
//                fiberUserDataFolderName +//.fiber
//                separator +
//                classeurDirectoryName +//output
//                separator +
//                classeurPathName//recapClasseur.xlsx
//
//        File g = new File(path +
//                separator +
//                fiberUserDataFolderName)//.fiber
//        g.exists() && g.directory ?: g.mkdir()
//
//        File i = new File(path +
//                separator +
//                fiberUserDataFolderName +
//                separator +
//                classeurDirectoryName)//output
//        i.exists() && i.directory ?: i.mkdir()
//
//        File f = new File(strRecapPath)
//        f.exists() ?: f.createNewFile()//recapClasseur.xlsx
//
//
//        this.classeur = new SpreadsheetRecap(
//                classeurPathName: strRecapPath,
//                nbFeuille: service.countMois(),
//                nomFeuilles: this.nomFeuilles(),
//                moisParAnnee: service.findAllMoisFormatFrParAnnee())
//        this.classeur
//    }
//
//    static String getSeparator() {
//        Paths.get(System.getProperty("user.home"))
//                .fileSystem.separator
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    Recap processRecap(String nomFeuilles, Integer moisInt, Integer anneeIntValue) {
//        new Recap(
//                sheetName: nomFeuilles,
//                inters: dao.findAllDeMoisDansAnnee(
//                        moisInt, anneeIntValue).collect {
//                    InterventionEntity it ->
//                        new InterventionDto(it)
//                },
//                annee: anneeIntValue,
//                mois: moisInt,
//                nbInterTotal: dao
//                        .countInterParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBaocBaap: dao
//                        .countRacParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBafa: dao
//                        .countBafaParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBast: dao
//                        .countBastParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPlp: dao
//                        .countPlpParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbSav: dao
//                        .countSavParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcTotal: dao
//                        .countPdcParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBafa: dao
//                        .countPdcBafaParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBast: dao
//                        .countPdcBastParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBaocBaap: dao
//                        .countPdcBaocBaapParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                labelTitreRecap:
//                        "${Recap.PRE_LABEL_TITRE_RECAP}" +
//                                "${InterUtils.convertNombreEnMois moisInt}" +
//                                " $anneeIntValue",
//                labelCurrentMonthYearFormattedFr:
//                        InterUtils.convertNombreEnMois(moisInt))
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    SpreadsheetRecap processFeuilles() {
//        init()
//        List<List<Integer>> listIntMoisAnnee =
//                dao.distinctMoisParAnnee()
//        classeur.recaps = new ArrayList<Recap>(classeur.nbFeuille)
//        assert classeur.nbFeuille == classeur.moisParAnnee.size()
//        assert classeur.nbFeuille == listIntMoisAnnee.size()
//
//        if (classeur.nbFeuille == null || classeur.nbFeuille <= 0) {
//            classeur.recaps = new ArrayList<Recap>()
//            classeur.nbFeuille = 0
//        } else {
//            classeur.moisParAnnee.eachWithIndex {
//                Map<String, Integer> map, int idx ->
//                    String moisStrKey = map.keySet().first()
//                    Integer anneeIntValue = map.get(moisStrKey)
//                    Integer moisInt = listIntMoisAnnee.get(idx).first()
//                    classeur.recaps.add idx,
//                            processRecap(classeur
//                                    .nomFeuilles
//                                    .get(idx),
//                                    moisInt,
//                                    anneeIntValue)
//            }
//        }
//        classeur
//    }
//
//    @Override
//    SpreadsheetRecap processClasseurFeuilles(String classeurPath) {
//        this.path = classeurPath
//        this.processFeuilles()
//        this.classeur.createExcelWorkBook()
//        classeur
//    }
}
