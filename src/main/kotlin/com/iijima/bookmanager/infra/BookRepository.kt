package com.iijima.bookmanager.infra

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.dao.AuthorDao
import com.iijima.bookmanager.infra.dao.BookDao
import com.iijima.bookmanager.infra.dao.PublisherDao
import com.iijima.bookmanager.infra.entity.AuthorRecord
import com.iijima.bookmanager.infra.entity.BookRecord
import com.iijima.bookmanager.infra.entity.BookSummary
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

    /**
     * 全ての [Book] を返す。
     */
    fun find(): List<Book> = bookDao.selectBookSummaries().map{ it.toBook() }

    /**
     * 条件に合致する [Book] を返す。
     */
    fun findBy(title: String, authorId: Int?, publisherId: Int?): List<Book> {
        val titleCondition = if (title.isEmpty()) {
            null
        } else {
            "%$title%"
        }
        return bookDao.selectBookSummariesBy(titleCondition, authorId, publisherId).map{ it.toBook() }
    }

    /**
     * [Book] を新規登録する。
     */
    fun save(book: Book) {
        val bookRecord = BookRecord(null, book.title, book.author.id, book.publisher.id)
        bookDao.insert(bookRecord)
    }

    /**
     * [Book] を更新する。
     */
    fun update(book: Book) {
        val bookRecord = BookRecord(book.id, book.title, book.author.id, book.publisher.id)
        bookDao.update(bookRecord)
    }

    /**
     * [Book] を削除する。
     */
    fun delete(id: Int) {
        bookDao.delete(id)
    }

    /**
     * [BookSummary] を [Book] に変換する。
     */
    private fun BookSummary.toBook(): Book =
            Book(id, title, Author(authorId, authorName), Publisher(publisherId, publisherName))

}
