package com.iijima.bookmanager.infra.repository

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.dao.BookDao
import com.iijima.bookmanager.infra.entity.BookRecord
import com.iijima.bookmanager.infra.entity.BookSummary
import com.nhaarman.mockito_kotlin.*
import org.junit.Test
import org.assertj.core.api.Assertions.assertThat
import org.seasar.doma.jdbc.Result

class BookRepositoryTest {

    private val bookDaoMock = mock<BookDao> {
        on { selectBookSummaries() } doReturn listOf(
                BookSummary(1, "testbook1", 1, "testauthor1", 1, "testpublisher1"),
                BookSummary(2, "testbook2", 2, "testauthor2", 2, "testpublisher2")
        )

        on { selectBookSummariesBy(any(), any(), any()) } doReturn listOf(
                BookSummary(1, "testbook1", 1, "testauthor1", 1, "testpublisher1")
        )

        on { insert(any()) } doReturn Result(1, BookRecord(1, "inserted_testbook", 3, 3))
        on { update(any()) } doReturn Result(1, BookRecord(1, "updated_testbook", 1, 1))
        on { delete(any()) } doReturn 1
        on { countSameBook(any()) } doReturn 1
    }

    // 状態を持たないため、Testクラスのフィールドで宣言する
    private val bookRepository = BookRepository(bookDaoMock)

    @Test
    fun find() {
        val books = bookRepository.find()
        assertThat(books.size).isEqualTo(2)

        val book = books.first{ it.title == "testbook1" }
        assertThat(book.author.id).isEqualTo(1)
        assertThat(book.publisher.id).isEqualTo(1)
        verify(bookDaoMock, times(1)).selectBookSummaries()
    }

    @Test
    fun findBy() {
        bookRepository.findBy("testbook1", 1, 1)
        verify(bookDaoMock, times(1)).selectBookSummariesBy(any(), any(), any())
    }

    @Test
    fun save() {
        val book = Book(
                3, "inserted_book",
                Author(3, "inserted_author"),
                Publisher(3, "inserted_publisher")
        )
        bookRepository.save(book)
        verify(bookDaoMock, times(1)).insert(any())
    }

    @Test
    fun update() {
        val book = Book(
                1, "updated_book",
                Author(1, "testauthor1"),
                Publisher(1, "testpublisher1")
        )
        bookRepository.update(book)
        verify(bookDaoMock, times(1)).update(any())
    }

    @Test
    fun delete() {
        bookRepository.delete(1)
        verify(bookDaoMock, times(1)).delete(any())
    }

    @Test
    fun countSameBook() {
        val book = Book(
                1, "testbook1",
                Author(1, "testauthor1"),
                Publisher(1, "testpublisher1")
        )
        bookRepository.countSameBook(book)
        verify(bookDaoMock, times(1)).countSameBook(any())
    }

}
