package com.cheroliv.fiber.inter.model

import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.inter.dto.InterDto

import java.time.LocalDateTime

class NullIntervention {
    Optional<InterDto> getOptionalInter(){
        Optional.empty()
    }
    String getNd(){''}
    TypeInterEnum getTypeInter(){null}
    ContractEnum getContract(){null}
    LocalDateTime getDateTimeInter(){null}
    String getFirstNameClient(){''}
    String getLastNameClient(){''}
}
