package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.model.InterDto
import groovy.transform.TypeChecked
import groovy.util.logging.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Slf4j
@Service
@TypeChecked
@Transactional
class InterServiceImpl implements InterService {

    @Override
    InterDto getFirst() {
        return null
    }

    @Override
    InterDto getPrevious(Long id) {
        return null
    }

    @Override
    void create(InterDto interDto) {

    }

    @Override
    void getNextInter(Long id) {

    }

    @Override
    InterDto getNext(Long id) {
        return null
    }

    @Override
    InterDto getLast() {
        return null
    }
}
