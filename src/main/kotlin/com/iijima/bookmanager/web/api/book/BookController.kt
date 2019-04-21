package com.iijima.bookmanager.web.api.book

import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.infra.BookRepository
import com.iijima.bookmanager.infra.entity.BookRecord
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/book")
class BookController (
        private val bookRepository: BookRepository
) {

    @RequestMapping("/get")
    fun get(): List<Book> {
        val books = bookRepository.find()
        return books
    }

    @RequestMapping("/create")
    fun create(@RequestBody book: Book) {
        bookRepository.save(book)
    }

    @RequestMapping("/update")
    fun update(@RequestBody book: Book) {
        bookRepository.update(book)
    }

    @RequestMapping("/delete")
    fun delete(@RequestBody id: Int) {
        bookRepository.delete(id)
    }

}
