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

    @Override
    InterDto get(Long id) {
        if (!id) return null
        Optional<Inter> optional = repo.findById(id)
        if (!optional.present) return null
        else new InterDto(
                id: optional.get().id,
                nd: optional.get().nd,
                firstName: optional.get().firstNameClient,
                lastName: optional.get().lastNameClient,
                contract: optional.get().contract.name(),
                typeInter: optional.get().typeInter.name(),
                dateTime: optional.get().dateTimeInter)
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
    InterDto getNext(Long id) {
        null
    }

    @Override
    InterDto getLast() {
        null
    }

    @Override
    Boolean isUniqueIndexAvailable(String nd, String type) {
        null
    }

    @Override
    void delete(Long id) {

    }

    @Override
    InterDto findById(Long id) {
        null
    }

    @Override
    Boolean isUniqueIndexConsistent(Long id, String nd, String type) {
        return null
    }

    @Override
    InterDto saveWithPatch(InterDto interDto) {
        null
    }
}
