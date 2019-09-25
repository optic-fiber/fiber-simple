package com.cheroliv.fiber.service


import org.springframework.context.support.AbstractMessageSource
import org.springframework.stereotype.Service

import java.text.MessageFormat


@Service
class MessageServiceImpl extends AbstractMessageSource implements MessageService {

    @Override
    MessageFormat resolveCode(final String code, final Locale locale) {
        null
    }
}
