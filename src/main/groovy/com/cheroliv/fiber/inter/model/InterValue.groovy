package com.cheroliv.fiber.inter.model

import groovy.transform.Immutable
import groovy.transform.TypeChecked

@TypeChecked
@Immutable(knownImmutables = ['optionalInter'])
class InterValue implements Serializable {
    Optional<InterDto> optionalInter
}
