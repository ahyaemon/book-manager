package com.iijima.bookmanager.domain

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.repository.AuthorRepository
import com.iijima.bookmanager.infra.repository.BookRepository
import com.iijima.bookmanager.infra.repository.PublisherRepository
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test


class BookServiceTest {

    // テスト用データ
    private val testAuthor1 = Author(1, "testauthor1")
    private val testPublisher1 = Publisher(1, "testpublisher1")
    private val testBook1 = Book(1, "testbook1", testAuthor1, testPublisher1)
    private val testAuthor2 = Author(2, "testauthor2")
    private val testPublisher2 = Publisher(2, "testpublisher2")
    private val testBook2 = Book(2, "testbook2", testAuthor1, testPublisher1)

    private val mockBookRepository = mock<BookRepository> {
        on { find() } doReturn listOf(testBook1, testBook2)
        on { findBy(any(), any(), any()) } doReturn listOf(testBook1)
    }

    private val mockAuthorRepository = mock<AuthorRepository> {
        on { find() } doReturn listOf(testAuthor1, testAuthor2)
        on { save(any()) } doReturn listOf(testAuthor1)
    }

    private val mockPublisherRepository = mock<PublisherRepository> {
        on { find() } doReturn listOf(testPublisher1, testPublisher2)
        on { save(any()) } doReturn listOf(testPublisher1)
    }

    private val bookService = BookService(mockBookRepository, mockAuthorRepository, mockPublisherRepository)

    @Before
    fun setup() {
        // mockBookRepository 初期化時に void メソッドのモックができなかったため、ここでやる
        doNothing().whenever(mockBookRepository).delete(any())
        doNothing().whenever(mockBookRepository).save(any())
        doNothing().whenever(mockBookRepository).update(any())
    }

    @Test
    fun find() {
        val books = bookService.find()
        assertThat(books.size).isEqualTo(2)
        verify(mockBookRepository, times(1)).find()

        val book = books.first{ it.id == 1 }
        assertThat(book.title).isEqualTo("testbook1")
        assertThat(book.author.id).isEqualTo(1)
        assertThat(book.publisher.id).isEqualTo(1)
    }

    @Test
    fun findBy() {
        val books = bookService.findBy("testbook1", 1, 1)
        assertThat(books.size).isEqualTo(1)
        verify(mockBookRepository, times(1)).findBy(any(), any(), any())

        val book = books.first{ it.id == 1 }
        assertThat(book.title).isEqualTo("testbook1")
        assertThat(book.author.id).isEqualTo(1)
        assertThat(book.publisher.id).isEqualTo(1)
    }

    @Test
    fun findAuthors() {
        val authors = bookService.findAuthors()
        assertThat(authors.size).isEqualTo(2)
        verify(mockAuthorRepository, times(1)).find()

        val author = authors.first{ it.id == 1 }
        assertThat(author.name).isEqualTo("testauthor1")
    }

    @Test
    fun findPublishers() {
        val publishers = bookService.findPublishers()
        assertThat(publishers.size).isEqualTo(2)
        verify(mockPublisherRepository, times(1)).find()

        val publisher = publishers.first {it.id == 1 }
        assertThat(publisher.name).isEqualTo("testpublisher1")
    }

    @Test
    fun save_authorもpublisherも存在する() {
        val book = Book(null, "testbook3", Author(3, "testauthor3"), Publisher(3, "testpublisher3"))
        bookService.save(book)
        verify(mockAuthorRepository, times(0)).save(any())
        verify(mockPublisherRepository, times(0)).save(any())
        verify(mockBookRepository, times(1)).save(any())
    }

    @Test
    fun save_authorが存在しない() {
        val book = Book(null, "testbook3", Author(null, "testauthor3"), Publisher(3, "testpublisher3"))
        bookService.save(book)
        verify(mockAuthorRepository, times(1)).save(any())
        verify(mockPublisherRepository, times(0)).save(any())
        verify(mockBookRepository, times(1)).save(any())
    }

    @Test
    fun save_publisherが存在しない() {
        val book = Book(null, "testbook3", Author(3, "testauthor3"), Publisher(null, "testpublisher3"))
        bookService.save(book)
        verify(mockAuthorRepository, times(0)).save(any())
        verify(mockPublisherRepository, times(1)).save(any())
        verify(mockBookRepository, times(1)).save(any())
    }

    @Test
    fun update_authorもpublisherも存在する() {
        val book = Book(null, "testbook3", Author(3, "testauthor3"), Publisher(3, "testpublisher3"))
        bookService.update(book)
        verify(mockAuthorRepository, times(0)).save(any())
        verify(mockPublisherRepository, times(0)).save(any())
        verify(mockBookRepository, times(1)).update(any())
    }

    @Test
    fun update_authorが存在しない() {
        val book = Book(null, "testbook3", Author(null, "testauthor3"), Publisher(3, "testpublisher3"))
        bookService.update(book)
        verify(mockAuthorRepository, times(1)).save(any())
        verify(mockPublisherRepository, times(0)).save(any())
        verify(mockBookRepository, times(1)).update(any())
    }

    @Test
    fun update_publisherが存在しない() {
        val book = Book(null, "testbook3", Author(null, "testauthor3"), Publisher(null, "testpublisher3"))
        bookService.update(book)
        verify(mockAuthorRepository, times(1)).save(any())
        verify(mockPublisherRepository, times(1)).save(any())
        verify(mockBookRepository, times(1)).update(any())
    }

    @Test
    fun delete() {
        bookService.delete(1)
        verify(mockBookRepository, times(1)).delete(any())
    }

    @Test
    fun hasSameBook_同じ本がある() {
        doReturn(1).whenever(mockBookRepository).countSameBook(any())
        val sameBookExists = bookService.hasSameBook(testBook1)
        assertThat(sameBookExists).isTrue()
    }

    @Test
    fun hasSameBook_同じ本がない() {
        doReturn(0).whenever(mockBookRepository).countSameBook(any())
        val sameBookExists = bookService.hasSameBook(testBook1)
        assertThat(sameBookExists).isFalse()
    }

}