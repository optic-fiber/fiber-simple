package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.model.InterDto
import com.cheroliv.fiber.inter.repository.InterRepository
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import javax.validation.Validator

@Slf4j
@Service
@TypeChecked
@Transactional(readOnly = true)
class InterServiceImpl implements InterService {

    @Autowired
    InterRepository repo
    @Autowired
    Validator validator
    static final def FIRST_ITERATION_OF_LIST = 0

    @Override
    InterDto get(Long id) {
        if (!id) return null
        Optional<Inter> optional = repo.findById(id)
        if (!optional.present) return null
        else new InterDto(optional.get())
    }

    @Override
    InterDto getFirst() {
        Long id
        InterDto result = null
        List<Optional<Inter>> optionals = repo.findByDateTimeInterMin()
        if (optionals.empty) return null
        optionals.eachWithIndex { Optional<Inter> it, int idx ->
            if (idx == FIRST_ITERATION_OF_LIST) {
                id = it.get().id
                result = new InterDto(it.get())
            }
            if (id < it.get().id) {
                id = it.get().id
                result = new InterDto(it.get())
            }
        }
        result
    }


    @Override
    InterDto getPrevious(Long id) {
        Optional optional = repo.findById(id)
        if (optional.isEmpty()) return null
        def result = optional.get()
        def firstDto = getFirst()
        if (id == firstDto.id) return firstDto
        //le previous c'est la premiere date inferieur au current
        // le max des min(current.date)
        new InterDto(this.repo.previous(id).get())
    }

    @Override
    InterDto getNext(Long id) {
        null
    }

    @Override
    InterDto getLast() {
        null
    }

    @Override
    InterDto findById(Long id) {
        null
    }

    @Override
    Boolean isUniqueIndexAvailable(String nd, String type) {
        null
    }

    @Override
    Boolean isUniqueIndexConsistent(Long id, String nd, String type) {
        return null
    }

    @Override
    @Transactional
    InterDto save(InterDto interDto) {
        null
    }

    @Override
    void delete(Long id) {

    }

    @Override
    InterDto saveWithPatch(InterDto interDto) {
        null
    }
}
