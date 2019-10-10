package com.cheroliv.fiber.inter.service

import com.cheroliv.fiber.inter.dao.InterDao
import com.cheroliv.fiber.inter.domain.InterventionEntity
import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.inter.dto.InterventionDto
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
    InterventionDto get(Long id) {
        if (!id) return null
        Optional<InterventionEntity> optional = dao.findById(id)
        if (!optional.present) return null
        else new InterventionDto(optional.get())
    }

    @Override
    InterventionDto getFirst() {
        InterventionDto result = new InterventionDto()
        Optional<InterventionEntity> optionalInter = dao.findByIdMin()
        if (optionalInter.present)
            result = new InterventionDto(optionalInter.get())
        result
    }

    @Override
    InterventionDto getLast() {
        InterventionDto result = new InterventionDto()
        Optional<InterventionEntity> optionalInter = dao.findByIdMax()
        if (optionalInter.present)
            result = new InterventionDto(optionalInter.get())
        result
    }

    @Override
    InterventionDto getPrevious(Long id) {
        Optional<InterventionEntity> optional = dao.findById(id)
        if (optional.isEmpty()) return null
        InterventionDto firstDto = getFirst()
        if (id == firstDto.id) return firstDto
        Optional<InterventionEntity> optionalResult
        while ((optionalResult = dao.findById(--id)).empty) {
            if (firstDto.id == optionalResult.get().id ||
                    id < firstDto.id)
                return firstDto
        }
        optionalResult.present ?
                new InterventionDto(optionalResult.get()) :
                new InterventionDto(optional.get())
    }

    @Override
    InterventionDto getNext(Long id) {
        Optional<InterventionEntity> optional = dao.findById(id)
        if (optional.isEmpty()) return null
        InterventionDto lastDto = getLast()
        log.info("id : $id")
        if (id == lastDto.id) return lastDto
        Optional<InterventionEntity> optionalResult
        InterventionDto interMaxId = getLast()
        while ((optionalResult = dao.findById(++id)).empty) {
            if (interMaxId.id == optionalResult.get().id ||
                    id > interMaxId.id)
                return interMaxId
        }
        optionalResult.present ?
                new InterventionDto(optionalResult.get()) :
                new InterventionDto(optional.get())
    }


    @Override
    InterventionDto findById(Long id) {
        if (!id) return null
        def optional = dao.findById(id)
        if (optional.isEmpty()) return null
        else new InterventionDto(optional.get())
    }


    @Override
    InterventionDto find(String nd, String type) {
        if (!TypeInterEnum.values().collect {
            it.name()
        }.contains(type)) throw new InterTypeEnumException(type)
        Optional<InterventionEntity> result = dao.find(
                nd, TypeInterEnum.valueOfName(type))
        if (result.present)
            new InterventionDto(result.get())
        else throw new InterEntityNotFoundException(nd, type)
    }


    @Override
    Boolean isUniqueIndexConsistent(Long id, String nd, String type) {
        if (!id || !nd || !type) return false
        if (dao.findById(id).empty) return false
        Optional<InterventionEntity> optional = dao.find(
                nd, TypeInterEnum.valueOfName(type))
        if (optional.empty) {
            return true
        }
        InterventionEntity result = optional.get()
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
    InterventionDto save(InterventionDto interDto) {
        new InterventionDto(dao.save(new InterventionEntity(
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
    InterventionDto saveWithPatch(InterventionDto interDto) {
        new InterventionDto(dao.saveAndFlush(new InterventionEntity(
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
    List<InterventionDto> getAll() {
        dao.findAll().collect { new InterventionDto(it) }
    }
}
