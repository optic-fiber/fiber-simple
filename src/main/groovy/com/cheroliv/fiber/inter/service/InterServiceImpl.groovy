package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.model.InterDto
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@TypeChecked
@Transactional(readOnly = true)
class InterServiceImpl implements InterService {

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
    void create(InterDto interDto) {

    }

    @Override
    @Transactional
    void update(InterDto interDto) {

    }

    @Override
    void getNextInter(Long id) {

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
