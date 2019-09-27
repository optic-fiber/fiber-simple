package com.cheroliv.fiber.inter.model


import groovy.transform.ToString

import java.time.ZonedDateTime

@ToString
class InterDto implements Serializable {

    Integer id
    String nd
    String firstName
    String lastName
    String contractEnum
    String interTypeEnum
    ZonedDateTime dateTime
}
