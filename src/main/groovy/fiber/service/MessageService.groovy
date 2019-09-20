package fiber.service


import java.text.MessageFormat

interface MessageService {
    MessageFormat resolveCode(String code, Locale locale)
}