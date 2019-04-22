package com.iijima.bookmanager.web.api.book.create

import com.iijima.bookmanager.web.error.ApiError

data class BookCreateResponse (
        val errors: List<ApiError>
)
