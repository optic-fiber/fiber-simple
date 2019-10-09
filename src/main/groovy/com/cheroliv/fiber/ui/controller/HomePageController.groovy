package com.cheroliv.fiber.ui.controller

import com.cheroliv.fiber.inter.service.InterService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class HomePageController {

    final InterService interService

    HomePageController(InterService interService) {
        this.interService = interService
    }

    @RequestMapping("/")
    String list(Model model) {
        model.addAttribute("list", interService.getAll())
        return "landing"
    }
}
