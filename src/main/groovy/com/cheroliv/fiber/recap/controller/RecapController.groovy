package com.cheroliv.fiber.recap.controller

import org.apache.poi.ss.usermodel.Workbook
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.view.document.AbstractXlsxView

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.time.LocalDateTime

@RestController
class RecapController extends AbstractXlsxView {
    def getRecap() {
    }

    def getRecap(LocalDateTime startDate, LocalDateTime endDate) {

    }

    @Override
    void buildExcelDocument(
            Map<String, Object> model,
            Workbook workbook,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
    }
}
