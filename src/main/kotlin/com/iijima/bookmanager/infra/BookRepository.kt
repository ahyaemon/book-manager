package com.iijima.bookmanager.infra

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.dao.BookDao
import org.springframework.stereotype.Repository

@Repository
class BookRepository (
        private val bookDao: BookDao
) {

    fun find(): List<Book> {
        return bookDao.selectBookSummaries().map{
            Book(
                    it.id,
                    it.title,
                    Author(it.authorId, it.authorName),
                    Publisher(it.publisherId, it.publisherName)
            )
        }
    }

}
