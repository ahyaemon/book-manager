package com.iijima.bookmanager.web.error

import org.springframework.validation.FieldError

class ApiError (
        val field: String,
        val message: String?
) {

    companion object {
        fun fromFieldError(error: FieldError): ApiError {
            return ApiError(error.field, error.defaultMessage)
        }
    }

}