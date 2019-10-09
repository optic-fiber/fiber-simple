package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.dao.InterDao
import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.inter.dto.InterDto
import com.cheroliv.fiber.inter.service.exceptions.InterEntityNotFoundException
import com.cheroliv.fiber.inter.service.exceptions.InterTypeEnumException
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
        InterDto result = new InterDto()
        Optional<Inter> optionalInter = dao.findByIdMin()
        if (optionalInter.present)
            result = new InterDto(optionalInter.get())
        result
    }

    @Override
    InterDto getLast() {
        InterDto result = new InterDto()
        Optional<Inter> optionalInter = dao.findByIdMax()
        if (optionalInter.present)
            result = new InterDto(optionalInter.get())
        result
    }

    @Override
    InterDto getPrevious(Long id) {
        Optional<Inter> optional = dao.findById(id)
        if (optional.isEmpty()) return null
        InterDto firstDto = getFirst()
        if (id == firstDto.id) return firstDto
        Optional<Inter> optionalResult
        while ((optionalResult = dao.findById(--id)).empty) {
            if (firstDto.id == optionalResult.get().id ||
                    id < firstDto.id)
                return firstDto
        }
        optionalResult.present ?
                new InterDto(optionalResult.get()) :
                new InterDto(optional.get())
    }

    @Override
    InterDto getNext(Long id) {
        Optional<Inter> optional = dao.findById(id)
        if (optional.isEmpty()) return null
        InterDto lastDto = getLast()
        log.info("id : $id")
        if (id == lastDto.id) return lastDto
        Optional<Inter> optionalResult
        InterDto interMaxId = getLast()
        while ((optionalResult = dao.findById(++id)).empty) {
            if (interMaxId.id == optionalResult.get().id ||
                    id > interMaxId.id)
                return interMaxId
        }
        optionalResult.present ?
                new InterDto(optionalResult.get()) :
                new InterDto(optional.get())
    }


    @Override
    InterDto findById(Long id) {
        if (!id) return null
        def optional = dao.findById(id)
        if (optional.isEmpty()) return null
        else new InterDto(optional.get())
    }


    @Override
    InterDto find(String nd, String type) {
        if (!TypeInterEnum.values().collect {
            it.name()
        }.contains(type)) throw new InterTypeEnumException(type)
        Optional<Inter> result = dao.find(
                nd, TypeInterEnum.valueOfName(type))
        if (result.present)
            new InterDto(result.get())
        else throw new InterEntityNotFoundException(nd, type)
    }


    @Override
    Boolean isUniqueIndexConsistent(Long id, String nd, String type) {
        if (!id || !nd || !type) return false
        if (dao.findById(id).empty) return false
        Optional<Inter> optional = dao.find(
                nd, TypeInterEnum.valueOfName(type))
        if (optional.empty) {
            return true
        }
        Inter result = optional.get()
        if (result.id == id) true
        else false
    }


    @Override
    Boolean isUniqueIndexAvailable(String nd, String type) {
        if (!nd || !type) return false
        Optional optional = dao.find(
                nd, TypeInterEnum.valueOfName(type))
        if (optional.empty) true
        else false
    }


    @Override
    @Transactional
    InterDto save(InterDto interDto) {
        new InterDto(dao.save(new Inter(
                nd: interDto.nd,
                typeInter: TypeInterEnum
                        .valueOfName(interDto.typeInter),
                contract: ContractEnum.valueOfName(interDto.contract),
                dateTimeInter: interDto.dateTime,
                firstNameClient: interDto.firstName,
                lastNameClient: interDto.lastName)))
    }

    @Override
    @Transactional
    void delete(Long id) {
        dao.deleteById(id)
    }

    @Override
    @Transactional
    InterDto saveWithPatch(InterDto interDto) {
        new InterDto(dao.saveAndFlush(new Inter(
                id: interDto.id,
                nd: interDto.nd,
                typeInter: TypeInterEnum
                        .valueOfName(interDto.typeInter),
                contract: ContractEnum.valueOfName(interDto.contract),
                dateTimeInter: interDto.dateTime,
                firstNameClient: interDto.firstName,
                lastNameClient: interDto.lastName)))
    }

    @Override
    List<InterDto> getAll() {
        dao.findAll().collect { new InterDto(it) }
    }
}
