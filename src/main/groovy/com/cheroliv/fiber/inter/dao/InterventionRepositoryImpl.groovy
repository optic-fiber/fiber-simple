package com.cheroliv.fiber.inter.dao

import com.cheroliv.fiber.inter.domain.Inter
import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.inter.dto.InterDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InterventionRepositoryImpl {
    final InterDao dao

    InterventionRepositoryImpl(InterDao dao) {
        this.dao = dao
    }

    InterDto save(InterDto dto){
        new InterDto(dao.save(new Inter(
                nd:dto.nd,
                typeInter: TypeInterEnum.valueOfName(dto.typeInter),
                contract: ContractEnum.valueOfName(dto.contract),
                dateTimeInter: dto.dateTime,
                firstNameClient: dto.firstName,
                lastNameClient: dto.lastName
        )))
    }
}
