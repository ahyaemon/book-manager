package com.iijima.bookmanager.web.api.author

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.infra.AuthorRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/author")
class AuthorController (
        private val authorRepository: AuthorRepository
) {

    @RequestMapping("/get")
    fun get(): List<Author> {
        return authorRepository.find()
    }

}
