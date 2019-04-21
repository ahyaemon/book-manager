package com.iijima.bookmanager.infra

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.dao.AuthorDao
import com.iijima.bookmanager.infra.dao.BookDao
import com.iijima.bookmanager.infra.dao.PublisherDao
import com.iijima.bookmanager.infra.entity.AuthorRecord
import com.iijima.bookmanager.infra.entity.BookRecord
import com.iijima.bookmanager.infra.entity.PublisherRecord
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
@Transactional
class BookRepository (
        private val bookDao: BookDao,
        private val authorDao: AuthorDao,
        private val publisherDao: PublisherDao
) {

    fun find(): List<Book> {
        return bookDao.selectBookSummaries().map{
            val author = Author(it.authorId, it.authorName)
            val publisher = Publisher(it.publisherId, it.publisherName)
            Book(it.id, it.title, author, publisher)
        }
    }

    fun save(book: Book) {
        val authorId = if (book.author.id == null) {
            val authorRecord = AuthorRecord(null, book.author.name)
            val authorResult = authorDao.insert(authorRecord)
            authorResult.entity.id
        } else {
            book.author.id
        }

        val publisherId = if (book.publisher.id == null) {
            val publisherRecord = PublisherRecord(null, book.publisher.name)
            val publisherResult = publisherDao.insert(publisherRecord)
            publisherResult.entity.id
        } else {
            book.publisher.id
        }

        val bookRecord = BookRecord(null, book.title, authorId, publisherId)
        bookDao.insert(bookRecord)
    }

    fun update(book: Book) {
        // TODO author, publisher については insert の可能性がある
        val bookRecord = BookRecord(book.id, book.title, book.author.id, book.publisher.id)
        bookDao.update(bookRecord)
    }

    fun delete(id: Int) {
        bookDao.delete(id)
    }

}
