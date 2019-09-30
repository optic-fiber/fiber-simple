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

    //TODO:METTRE DES HEADER DEDANS QUAND JE SAURAIS
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    ResponseEntity<InterDto> save(@RequestBody InterDto interDto)
            throws URISyntaxException {
        if (interDto.getId() != null)
            throw new InterIdAlreadyExistsBeforeSaveException()
        if (interService.isUniqueKey(
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
    InterDto update(@RequestBody InterDto interDto) {
        this.interService.update(interDto)
    }

    @PatchMapping(consumes = [APPLICATION_JSON_UTF8_VALUE])
    InterDto patch(InterDto interDto) {
        this.interService.update(interDto)
    }


    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) {
        if(!interService.findById(id))
            throw new InterIdNotExistsBeforeDeleteException()
        interService.delete(id)
        ResponseEntity.noContent()
                .headers(new HttpHeaders())
                .build()
    }

}

/*

@DeleteMapping("/questions/{id}")
public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
    log.debug("REST request to delete Question : {}", id);
    questionService.delete(id);
    return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
}


@PutMapping("/questions")
public ResponseEntity<QuestionDTO> updateQuestion(@Valid @RequestBody QuestionDTO questionDTO) throws URISyntaxException {
    log.debug("REST request to update Question : {}", questionDTO);
    if (questionDTO.getId() == null) {
        throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
    }
    QuestionDTO result = questionService.save(questionDTO);
    return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, questionDTO.getId().toString()))
            .body(result);
}

 */