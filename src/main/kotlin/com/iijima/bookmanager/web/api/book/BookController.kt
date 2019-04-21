package com.iijima.bookmanager.web.api.book

import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.infra.AuthorRepository
import com.iijima.bookmanager.infra.BookRepository
import com.iijima.bookmanager.infra.PublisherRepository
import com.iijima.bookmanager.web.api.book.delete.DeleteBookForm
import com.iijima.bookmanager.web.api.book.initialize.BookPageInitializeResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/book")
class BookController (
        private val bookRepository: BookRepository,
        private val authorRepository: AuthorRepository,
        private val publisherRepository: PublisherRepository
) {

    @RequestMapping("/get")
    fun get(): List<Book> {
        val books = bookRepository.find()
        return books
    }

    @RequestMapping("/initialize")
    fun initialize(): BookPageInitializeResponse {
        val books = bookRepository.find()
        val authors = authorRepository.find()
        val publishers = publisherRepository.find()
        return BookPageInitializeResponse(books, authors, publishers)
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
    fun delete(@RequestBody form: DeleteBookForm) {
        bookRepository.delete(form.id)
    }

}
