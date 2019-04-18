package com.iijima.bookmanager.infra

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.infra.dao.AuthorDao
import org.springframework.stereotype.Repository

@Repository
class AuthorRepository (
        private val authorDao: AuthorDao
){

    fun find(): List<Author> {
        val records = authorDao.select()
        return records.map{ Author(it.id, it.name) }
    }

}
