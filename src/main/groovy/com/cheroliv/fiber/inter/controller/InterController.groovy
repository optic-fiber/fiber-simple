package com.cheroliv.fiber.inter.controller


import com.cheroliv.fiber.inter.service.InterService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RestController

@RestController
//@RequestMapping("/inters")
class InterController {

    final InterService interResource

    @Autowired
    InterController(InterService interResource) {
        this.interResource = interResource
    }

//    @GetMapping
//    InterDto firstInter() {
//        this.interResource.getFirst()
//    }
//
//    InterDto previousInter(Long id) {
//        this.interResource.getPrevious(id)
//    }
//
//    void createInter(InterDto interDto) {
//        this.interResource.create(interDto)
//    }
//
//    void updateInter(InterDto interDto) {
//        this.interResource.getNextInter(id)
//    }
//
//    InterDto nextInter(Long id) {
//        this.interResource.getNext(id)
//    }
//
//    @GetMapping
//    InterDto lastInter() {
//        this.interResource.getLast()
//    }

}
