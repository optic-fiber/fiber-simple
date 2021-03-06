package com.cheroliv.fiber.inter.dto

import com.cheroliv.fiber.inter.domain.InterventionEntity
import com.cheroliv.fiber.inter.domain.InterConstants
import com.fasterxml.jackson.annotation.JsonFormat
import groovy.transform.ToString

import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import java.time.LocalDateTime

@ToString
class InterventionDto implements Serializable {
    InterventionDto() {
    }
    Long id
    @NotNull(message = InterConstants.ND_NOTNULL_CSTRT_TPL_MSG)
    @Size(min = 10, max = 10,
            message = InterConstants.ND_SIZE_CSTRT_TPL_MSG)
    String nd
    @Size(max = 100)
    String firstName
    @Size(max = 100)
    String lastName
    @NotNull
    @Pattern(regexp = "LM|IQ|CABLE_ROUTING")
    String contract
    @NotNull
    @Pattern(regexp = "BAAP|BAOC|BAFA|BAST|PLP|SAV")
    String typeInter
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    LocalDateTime dateTime


    InterventionDto(InterventionEntity i) {
        this.id = i.id
        this.nd = i.nd
        this.firstName = i.firstNameClient
        this.lastName = i.lastNameClient
        this.contract = i.contract.name()
        this.typeInter = i.typeInter.name()
        this.dateTime = i.dateTimeInter

    }

}
