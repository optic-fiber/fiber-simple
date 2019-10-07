package com.cheroliv.fiber.inter.model

import com.cheroliv.fiber.inter.dto.InterDto
import groovy.transform.Immutable
import groovy.transform.TypeChecked

@TypeChecked
@Immutable(knownImmutables = ['optionalInter'])
class InterValue implements Serializable {
    Optional<InterDto> optionalInter
}
