package com.cheroliv.fiber.inter.controller

import com.cheroliv.fiber.inter.controller.exceptions.*
import com.cheroliv.fiber.inter.model.InterDto
import com.cheroliv.fiber.inter.service.InterService
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE

@Slf4j
@TypeChecked
@RestController
@RequestMapping(value = INTER_BASE_URL_REST_API,
        produces = [APPLICATION_JSON_UTF8_VALUE])
class InterController {
    public static final String INTER_BASE_URL_REST_API = '/api/inters'

    final InterService interService

    InterController(InterService interService) {
        this.interService = interService
    }

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

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<InterDto> save(@RequestBody InterDto interDto)
            throws URISyntaxException {
        if (interDto.getId() != null)
            throw new InterIdAlreadyExistsBeforeSaveException()
        if (!interService.isUniqueIndexAvailable(
                interDto.nd,
                interDto.typeInter))
            throw new InterAlreadyExistsException()
        interService.save(interDto)
        String uri = "$INTER_BASE_URL_REST_API/${interDto.id}"
        ResponseEntity.created(new URI(uri))
                .headers(new HttpHeaders())
                .body(interDto)

    }

    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<InterDto> update(@RequestBody InterDto interDto) {
        if (!interDto.id)
            throw new InterIdNullBeforeUpdateException()
        if (!interService.isUniqueIndexConsistent(
                interDto.id,
                interDto.nd,
                interDto.typeInter))
            throw new InterAlreadyExistsException()
        InterDto result = this.interService.save(interDto)
        ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body(result)
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!interService.findById(id))
            throw new InterIdNotExistsBeforeDeleteException()
        interService.delete(id)
        ResponseEntity.noContent()
                .headers(new HttpHeaders())
                .build()
    }

    @PatchMapping(consumes = [APPLICATION_JSON_UTF8_VALUE])
    ResponseEntity<InterDto> patch(@RequestBody InterDto interDto) {
        if (!interDto.id)
            throw new InterIdNullBeforePatchException()
        if (interDto.nd && interDto.typeInter)
            if (!interService.isUniqueIndexConsistent(
                    interDto.id,
                    interDto.nd,
                    interDto.typeInter))
                throw new InterAlreadyExistsException()
        InterDto result = this.interService.saveWithPatch(interDto)
        ResponseEntity.ok()
                .headers(new HttpHeaders())
                .body(result)
    }


}