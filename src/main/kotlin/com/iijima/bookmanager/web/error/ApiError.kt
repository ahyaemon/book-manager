package com.iijima.bookmanager.web.error

import org.springframework.validation.FieldError

class ApiError (
        val field: String,
        val message: String?
) {

}
