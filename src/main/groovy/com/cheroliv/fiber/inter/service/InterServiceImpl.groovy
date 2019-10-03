package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.model.InterDto
import com.cheroliv.fiber.inter.repository.InterDao
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
    InterDao dao
    @Autowired
    Validator validator
    static final def FIRST_ITERATION_OF_LIST = 0

    @Override
    InterDto get(Long id) {
        if (!id) return null
        Optional<Inter> optional = dao.findById(id)
        if (!optional.present) return null
        else new InterDto(optional.get())
    }

    @Override
    InterDto getFirst() {
        Long id
        InterDto result = null
        List<Optional<Inter>> optionals =
                dao.findByMinDateTimeInter()
        if (optionals.empty) return null
        optionals.eachWithIndex {
            Optional<Inter> it, int idx ->
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
    InterDto getLast() {
        Long id
        InterDto result = null
        List<Optional<Inter>> optionals =
                dao.findByMaxDateTimeInter()
        if (optionals.empty) return null
        optionals.eachWithIndex {
            Optional<Inter> it, int idx ->
                if (idx == FIRST_ITERATION_OF_LIST) {
                    id = it.get().id
                    result = new InterDto(it.get())
                }
                if (id > it.get().id) {
                    id = it.get().id
                    result = new InterDto(it.get())
                }
        }
        result
    }

    /**
     * le previous c'est la premiere date
     * inferieur au current
     * le max des min(current.date)
     * @param id
     * @return
     */
    @Override
    InterDto getPrevious(Long id) {
        Optional optional = dao.findById(id)
        if (optional.isEmpty()) return null
        InterDto firstDto = getFirst()
        if (id == firstDto.id) return firstDto
        new InterDto(dao.previous(id).get())
    }

    @Override
    InterDto getNext(Long id) {
        Optional optional = dao.findById(id)
        if (optional.isEmpty()) return null
        InterDto lastDto = getLast()
        if (id == lastDto.id) return lastDto
        new InterDto(dao.next(id).get())
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
