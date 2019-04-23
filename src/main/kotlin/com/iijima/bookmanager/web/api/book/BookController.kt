package com.iijima.bookmanager.web.api.book

import com.iijima.bookmanager.domain.BookService
import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.web.api.book.create.BookCreateForm
import com.iijima.bookmanager.web.api.book.create.BookCreateResponse
import com.iijima.bookmanager.web.api.book.delete.BookDeleteForm
import com.iijima.bookmanager.web.api.book.initialize.BookPageInitializeResponse
import com.iijima.bookmanager.web.api.book.update.BookUpdateForm
import com.iijima.bookmanager.web.api.book.update.BookUpdateResponse
import com.iijima.bookmanager.web.error.ApiError
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/book")
class BookController (
        private val bookService: BookService
) {

    /**
     * 全ての [Book], [Author], [Publisher] を取得する。
     * ページ表示時などで、まとめて取得する用。
     */
    @GetMapping("/initialize")
    fun initialize(): BookPageInitializeResponse {
        val books = bookService.find()
        val authors = bookService.findAuthors()
        val publishers = bookService.findPublishers()
        return BookPageInitializeResponse(books, authors, publishers)
    }

    /**
     * 全ての [Book] を取得する。
     */
    @GetMapping("/get")
    fun get(): List<Book> = bookService.find()

    /**
     * 条件に合致する [Book] を取得する。
     */
    @GetMapping("/search")
    fun search(
            @RequestParam title: String,
            @RequestParam(required = false) authorId: Int?,
            @RequestParam(required = false) publisherId: Int?
    ): List<Book> = bookService.findBy(title, authorId, publisherId)

    /**
     * 全ての [Author] を取得する。
     */
    @GetMapping("/getAuthors")
    fun getAuthors(): List<Author> = bookService.findAuthors()

    /**
     * 全ての [Publisher] を取得する。
     */
    @GetMapping("/getPublishers")
    fun getPublishers(): List<Publisher> = bookService.findPublishers()

    /**
     * [Book] を新規登録する。
     * [Author] が存在しない場合は新規登録する。
     * [Publisher] が存在しない場合は新規登録する。
     */
    @PostMapping("/create")
    fun create(@RequestBody @Validated form: BookCreateForm, bindingResult: BindingResult): BookCreateResponse {
        if (bindingResult.hasErrors()) {
            val errors = bindingResult.fieldErrors.map{ ApiError(it.field, it.defaultMessage) }
            return BookCreateResponse(errors)
        }
        val book = form.toBook()
        bookService.save(book)
        return BookCreateResponse(listOf())
    }

    /**
     * [Book] を更新する。
     * [Author] が存在しない場合は新規登録する。
     * [Publisher] が存在しない場合は新規登録する。
     */
    @PostMapping("/update")
    fun update(@RequestBody @Validated form: BookUpdateForm, bindingResult: BindingResult): BookUpdateResponse {
        if (bindingResult.hasErrors()) {
            val errors = bindingResult.fieldErrors.map {ApiError(it.field, it.defaultMessage) }
            return BookUpdateResponse(errors)
        }
        val book = form.toBook()
        bookService.update(book)
        return BookUpdateResponse(listOf())
    }

    /**
     * [Book] を削除する。
     */
    @PostMapping("/delete")
    fun delete(@RequestBody form: BookDeleteForm) {
        bookService.delete(form.id)
    }

}
