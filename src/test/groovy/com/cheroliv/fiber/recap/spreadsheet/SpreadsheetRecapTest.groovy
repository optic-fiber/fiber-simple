package com.cheroliv.fiber.recap.spreadsheet

//import com.cheroliv.fiber.AbstractInterTestCase
//import com.cheroliv.com.cheroliv.fiber.inter.service.InterDataService
//import com.cheroliv.com.cheroliv.fiber.recap.service.RecapService
//import groovy.transform.CompileStatic
//import groovy.util.logging.Slf4j
//import org.apache.poi.ss.usermodel.Sheet
//import org.apache.poi.ss.usermodel.Workbook
//import org.apache.poi.ss.usermodel.WorkbookFactory
//import org.junit.jupiter.api.BeforeEach
//import org.junit.jupiter.api.MethodOrderer
//import org.junit.jupiter.api.Order
//import org.junit.jupiter.api.Test
//import org.junit.jupiter.api.TestMethodOrder
//import org.junit.jupiter.api.extension.ExtendWith
//import org.springframework.beans.factory.annotation.Autowired
//import org.springframework.boot.test.context.SpringBootTest
//import org.springframework.core.io.Resource
//import org.springframework.test.context.ActiveProfiles
//import org.springframework.test.context.junit.jupiter.SpringExtension
//
//@Slf4j
//@CompileStatic
//@SpringBootTest
//@ActiveProfiles("test")
//@TestMethodOrder(MethodOrderer.OrderAnnotation)
class SpreadsheetRecapTest
//        extends AbstractInterTestCase
{

//    @Autowired
//    RecapService recapService
//    @Autowired
//    InterDataService interService
//
//   @BeforeEach
//    void setUp() {
//        super.setUp()
//    }
//
//    @Test
//    @Order(1)
//    void testCreateExcelWorkBook() {
//        List<String> nomFeuilles = recapService.nomFeuilles()
//        String rootPathData = new File(applicationContext
//                .getResource("/").URI).path
//        recapService.processClasseurFeuilles(rootPathData)
//        Resource resource = applicationContext
//                .getResource("file:" +
//                        "${rootPathData}${separator}" +
//                        "${fiberUserDataFolderName}${separator}" +
//                        "${classeurDirectoryName}${separator}" +
//                        "$classeurPathName")
//
//        assert resource.exists()
//        assert resource.file.exists()
//        assert !resource.file.directory
//        File wbFile = resource.file
//        FileInputStream stream = new FileInputStream(wbFile)
//        Workbook wb = WorkbookFactory.create stream
//        assert interService.countMois()== wb.numberOfSheets
//        wb.sheetIterator().toList().eachWithIndex { Sheet sheet, int idx ->
//            assert nomFeuilles.get(idx)==
//                    sheet.sheetName
//        }
//        wb.close()
//    }
}
