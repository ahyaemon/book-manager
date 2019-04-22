package com.iijima.bookmanager.web.api.book

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.AuthorRepository
import com.iijima.bookmanager.infra.BookRepository
import com.iijima.bookmanager.infra.PublisherRepository
import com.iijima.bookmanager.web.api.book.create.BookCreateForm
import com.iijima.bookmanager.web.api.book.create.BookCreateResponse
import com.iijima.bookmanager.web.api.book.delete.BookDeleteForm
import com.iijima.bookmanager.web.api.book.initialize.BookPageInitializeResponse
import com.iijima.bookmanager.web.error.ApiError
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
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
    fun create(@RequestBody @Validated form: BookCreateForm, bindingResult: BindingResult): BookCreateResponse {
        if (bindingResult.hasErrors()) {
            val errors = bindingResult.fieldErrors.map{ ApiError(it.field, it.defaultMessage) }
            return BookCreateResponse(errors)
        }
        val book = Book(
                form.id,
                form.title,
                Author(form.author.id, form.author.name),
                Publisher(form.publisher.id, form.publisher.name)
        )
        bookRepository.save(book)
        return BookCreateResponse(listOf())
    }

    @RequestMapping("/update")
    fun update(@RequestBody book: Book) {
        bookRepository.update(book)
    }

    @RequestMapping("/delete")
    fun delete(@RequestBody form: BookDeleteForm) {
        bookRepository.delete(form.id)
    }

}
