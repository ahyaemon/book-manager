package com.iijima.bookmanager.web.api.db

import org.flywaydb.core.Flyway
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/db")
class DbController (
        private val flyway: Flyway
) {

    @PostMapping("/init")
    fun init() {
        flyway.clean()
        flyway.migrate()
    }

}
