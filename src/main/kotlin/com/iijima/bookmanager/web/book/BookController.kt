package com.iijima.bookmanager.web.book

import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.infra.BookRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController (
        private val bookRepository: BookRepository
) {

    @RequestMapping("/get")
    fun get(): List<Book> {
        val books = bookRepository.find()
        return books
    }

}
