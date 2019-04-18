package com.iijima.bookmanager.web.book

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.infra.AuthorRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController (
        private val authorRepository: AuthorRepository
) {

    @RequestMapping("/get")
    fun get(): List<Author> {
        val authors = authorRepository.find()
        return authors
    }

}
