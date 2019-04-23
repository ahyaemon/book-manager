package com.iijima.bookmanager.web.api.book.update

import com.iijima.bookmanager.web.error.ApiError

data class BookUpdateResponse (
        val errors: List<ApiError>
)
