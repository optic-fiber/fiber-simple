package com.cheroliv.fiber.ui.controller

import com.cheroliv.fiber.inter.service.InterService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomePageController {

    final InterService interService

    HomePageController(InterService interService) {
        this.interService = interService
    }

    @RequestMapping("/")
    String index() {
        "index"
    }
}
