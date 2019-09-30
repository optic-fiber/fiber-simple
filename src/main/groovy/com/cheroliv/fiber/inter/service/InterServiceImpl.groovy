package com.cheroliv.fiber.inter.service

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
    InterRepository interRepository
    @Autowired
    Validator validator

    @Override
    InterDto get(Long id) {
        null
    }

    @Override
    InterDto getFirst() {
        null
    }


    @Override
    InterDto getPrevious(Long id) {
        null
    }

    @Override
    @Transactional
    InterDto save(InterDto interDto) {
        null
    }

    @Override
    @Transactional
    InterDto update(InterDto interDto) {
        null
    }

    @Override
    InterDto getNext(Long id) {
        null
    }

    @Override
    InterDto getLast() {
        null
    }
}
