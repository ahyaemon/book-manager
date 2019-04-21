package com.iijima.bookmanager.web.api.book.initialize

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher

data class BookPageInitializeResponse (
        val books: List<Book>,
        val authors: List<Author>,
        val publishers: List<Publisher>
)
