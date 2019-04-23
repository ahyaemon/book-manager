package com.iijima.bookmanager.web.api.book.select

data class SearchCondition (
        val title: String,
        val authorId: Int?,
        val publisherId: Int?
)
