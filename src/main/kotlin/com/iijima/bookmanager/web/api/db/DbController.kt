package com.iijima.bookmanager.web.api.db

import org.flywaydb.core.Flyway
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.sql.DataSource

@RestController
@RequestMapping("/api/db")
class DbController (
        private val dataSource: DataSource
) {

    @PostMapping("/init")
    fun init() {
        val flyway = Flyway.configure().load()
        flyway.dataSource = dataSource
        flyway.clean()
        flyway.migrate()
    }

}
