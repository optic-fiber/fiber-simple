package com.cheroliv.fiber.inter.controller

import com.cheroliv.fiber.inter.model.InterDto
import com.cheroliv.fiber.inter.service.InterService
import groovy.transform.TypeChecked
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@TypeChecked
@RestController
class InterController {

    final InterService interResource

    InterController(InterService interResource) {
        this.interResource = interResource
    }

    @GetMapping('/inters/first')
    InterDto getFirstInter() {
        InterDto result = this.interResource.getFirst()
        if (result) result
        else throw new FirstInterNotFoundException()
    }


    @GetMapping('/inters/last')
    InterDto getLastInter() {
        InterDto result = this.interResource.getLast()
        if (result) result
        else throw new LastInterNotFoundException()
    }



    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void FirstInterNotFoundHandler(FirstInterNotFoundException e) {

    }



    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void LastInterNotFoundHandler(LastInterNotFoundException e) {

    }

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
//    InterDto getLastInter() {
//        this.interResource.getLast()
//    }

}
