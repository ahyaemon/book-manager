package com.iijima.bookmanager.infra

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.infra.dao.AuthorDao
import com.iijima.bookmanager.infra.entity.AuthorRecord
import org.springframework.stereotype.Repository

@Repository
class AuthorRepository (
        private val authorDao: AuthorDao
){

    /**
     * 全ての [Author] を返す。
     */
    fun find(): List<Author> {
        val records = authorDao.select()
        return records.map{ Author(it.id, it.name) }
    }

    /**
     * [Author] を登録する。
     * 登録した [Author] を返す。
     */
    fun save(author: Author): Author {
        val record = AuthorRecord(null, author.name)
        val result = authorDao.insert(record)
        return Author(result.entity.id, author.name)
    }

}
