package com.cheroliv.fiber.inter.controller

import com.cheroliv.fiber.inter.model.InterDto
import com.cheroliv.fiber.inter.service.InterService
import groovy.transform.TypeChecked
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@TypeChecked
@RestController
@RequestMapping(value = '/inters',
        produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class InterController {

    final InterService interService

    InterController(InterService interService) {
        this.interService = interService
    }

//TODO : chaining exception from service to controller
    @GetMapping(value = '/{id}')
    InterDto get(@PathVariable Long id) {
        InterDto result = this.interService.get(id)
        if (result) result
        else throw new InterNotFoundException()
    }

    @GetMapping(value = '/first')
    InterDto getFirst() {
        InterDto result = this.interService.getFirst()
        if (result) result
        else throw new FirstInterNotFoundException()
    }


    @GetMapping(value = '/last')
    InterDto getLast() {
        InterDto result = this.interService.getLast()
        if (result) result
        else throw new LastInterNotFoundException()
    }

    @GetMapping(value = '/{id}/prev')
    InterDto getPrevious(@PathVariable Long id) {
        InterDto result = this.interService.getPrevious(id)
        if (result) result
        else throw new PreviousInterNotFoundException()
    }


    @GetMapping(value = '/{id}/next')
    InterDto getNext(@PathVariable Long id) {
        InterDto result = this.interService.getNext(id)
        if (result) result
        else throw new NextInterNotFoundException()
    }


    @PostMapping
    void create(InterDto interDto) {
        this.interService.create(interDto)
    }

    @PutMapping
    void update(InterDto interDto) {
        this.interService.update(interDto)
    }

    @PatchMapping
    void patch(InterDto interDto) {
        this.interService.update(interDto)
    }


}
