package com.iijima.bookmanager.web.api.publisher

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.AuthorRepository
import com.iijima.bookmanager.infra.PublisherRepository
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/publisher")
class PublisherController (
        private val publisherRepository: PublisherRepository
) {

    @RequestMapping("/get")
    fun get(): List<Publisher> {
        return publisherRepository.find()
    }

}
