package com.iijima.bookmanager.infra

import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.infra.dao.BookDao
import org.springframework.stereotype.Repository

@Repository
class BookRepository (
        private val bookDao: BookDao
) {

    fun find(): List<Book> {
        return bookDao.selectBookSummary().map{ Book(it.id, it.title, it.author, it.publisher) }
    }

}