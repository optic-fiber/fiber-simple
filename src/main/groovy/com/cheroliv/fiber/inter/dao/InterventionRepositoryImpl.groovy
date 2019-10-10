package com.cheroliv.fiber.inter.dao


import com.cheroliv.fiber.inter.domain.InterventionEntity
import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.inter.dto.InterventionDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InterventionRepositoryImpl {
    final InterDao dao

    InterventionRepositoryImpl(InterDao dao) {
        this.dao = dao
    }

    InterventionDto save(InterventionDto dto){
        new InterventionDto(dao.save(new InterventionEntity(
                nd:dto.nd,
                typeInter: TypeInterEnum.valueOfName(dto.typeInter),
                contract: ContractEnum.valueOfName(dto.contract),
                dateTimeInter: dto.dateTime,
                firstNameClient: dto.firstName,
                lastNameClient: dto.lastName
        )))
    }
}
