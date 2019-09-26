package com.cheroliv.fiber.recap.service


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
//
//@Slf4j
//@Service
//@CompileStatic
class RecapServiceImpl
//        implements RecapService
{

//    final String classeurPathName
//    final String fiberUserDataFolderName
//    final String classeurDirectoryName
//    final InterDataService interService
//    final InterRepository interRepository
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
//    RecapServiceImpl(
//            @Value('${classeurPathName}')
//                    String classeurPathName,
//            @Value('${fiberUserDataFolderName}')
//                    fiberUserDataFolderName,
//            @Value('${classeurDirectoryName}')
//                    classeurDirectoryName,
//            InterDataService interService,
//            InterRepository interRepository) {
//        this.interRepository = interRepository
//        this.interService = interService
//        this.classeurPathName = classeurPathName
//        this.fiberUserDataFolderName = fiberUserDataFolderName
//        this.classeurDirectoryName = classeurDirectoryName
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    List<String> nomFeuilles() {
//        List<Map<String, Integer>> list = interService.findAllMoisFormatFrParAnnee()
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
//                nbFeuille: interService.countMois(),
//                nomFeuilles: this.nomFeuilles(),
//                moisParAnnee: interService.findAllMoisFormatFrParAnnee())
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
//                inters: interRepository
//                        .findAllDeMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                annee: anneeIntValue,
//                mois: moisInt,
//                nbInterTotal: interRepository
//                        .countInterParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBaocBaap: interRepository
//                        .countRacParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBafa: interRepository
//                        .countBafaParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbBast: interRepository
//                        .countBastParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPlp: interRepository
//                        .countPlpParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbSav: interRepository
//                        .countSavParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcTotal: interRepository
//                        .countPdcParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBafa: interRepository
//                        .countPdcBafaParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBast: interRepository
//                        .countPdcBastParMoisDansAnnee(
//                                moisInt, anneeIntValue),
//                nbPdcBaocBaap: interRepository
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
//                interRepository.distinctMoisParAnnee()
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
