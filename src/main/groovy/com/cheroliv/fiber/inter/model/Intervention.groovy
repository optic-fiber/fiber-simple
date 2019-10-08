package com.cheroliv.fiber.inter.model

import com.cheroliv.fiber.inter.domain.enumeration.ContractEnum
import com.cheroliv.fiber.inter.domain.enumeration.TypeInterEnum
import com.cheroliv.fiber.inter.dto.InterDto
import groovy.transform.CompileStatic
import groovy.transform.Immutable

import java.time.LocalDateTime

@CompileStatic
@Immutable(knownImmutables = ['optionalInter'])
class Intervention implements Serializable {
    Optional<InterDto> optionalInter
    String nd
    LocalDateTime dateTime
    String firstName
    String lastName
    ContractEnum contract
    TypeInterEnum typeInter
}
