package com.iijima.bookmanager.web.api.book

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.iijima.bookmanager.domain.BookService
import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.web.api.book.create.AuthorCreateForm
import com.iijima.bookmanager.web.api.book.create.BookCreateForm
import com.iijima.bookmanager.web.api.book.create.BookCreateResponse
import com.iijima.bookmanager.web.api.book.create.PublisherCreateForm
import com.iijima.bookmanager.web.api.book.delete.BookDeleteForm
import com.iijima.bookmanager.web.api.book.initialize.BookPageInitializeResponse
import com.iijima.bookmanager.web.api.book.update.AuthorUpdateForm
import com.iijima.bookmanager.web.api.book.update.BookUpdateForm
import com.iijima.bookmanager.web.api.book.update.PublisherUpdateForm
import com.iijima.bookmanager.web.error.ApiError
import com.nhaarman.mockito_kotlin.*
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest
class BookControllerTest {

    // テスト用データ
    private val testAuthor1 = Author(1, "testauthor1")
    private val testPublisher1 = Publisher(1, "testpublisher1")
    private val testBook1 = Book(1, "testbook1", testAuthor1, testPublisher1)
    private val testAuthor2 = Author(2, "testauthor2")
    private val testPublisher2 = Publisher(2, "testpublisher2")
    private val testBook2 = Book(2, "testbook2", testAuthor1, testPublisher1)

    private val mockBookService = mock<BookService> {
        on { find() } doReturn listOf(testBook1, testBook2)
        on { findAuthors() } doReturn listOf(testAuthor1, testAuthor2)
        on { findPublishers() } doReturn listOf(testPublisher1, testPublisher2)
        on { findBy(any(), any(), any()) } doReturn listOf(testBook1)
        on { findBy(any(), isNull(), any()) } doReturn listOf(testBook1)
        on { findBy(any(), any(), isNull()) } doReturn listOf(testBook1)
    }

    private val bookController = BookController(mockBookService)
    private val mapper = jacksonObjectMapper()
    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        // mockBookService 初期化時に void メソッドのモックができなかったため、ここでやる
        doNothing().whenever(mockBookService).save(any())
        doNothing().whenever(mockBookService).update(any())
        doNothing().whenever(mockBookService).delete(any())

        // 基本は false を返すが、true を返したい場合はケースごとに設定
        doReturn(false).whenever(mockBookService).hasSameBook(any())

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build()
    }

    @Test
    fun initialize() {
        val expected = BookPageInitializeResponse(
                listOf(testBook1, testBook2),
                listOf(testAuthor1, testAuthor2),
                listOf(testPublisher1, testPublisher2)
        )
        mockMvc.perform(get("/api/book/initialize"))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun get() {
        val expected = listOf(testBook1, testBook2)
        mockMvc.perform(get("/api/book/get"))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun search_全パラメータあり() {
        val expected = listOf(testBook1)
        mockMvc.perform(
                get("/api/book/search")
                        .param("title", "testBook1")
                        .param("authorId", "1")
                        .param("publisherId", "1"))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun search_authorIdなし() {
        val expected = listOf(testBook1)
        mockMvc.perform(
                get("/api/book/search")
                        .param("title", "testBook1")
                        .param("publisherId", "1"))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun search_publisherなし() {
        val expected = listOf(testBook1)
        mockMvc.perform(
                get("/api/book/search")
                        .param("title", "testBook1")
                        .param("authorId", "1"))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun getAuthors() {
        val expected = listOf(testAuthor1, testAuthor2)
        mockMvc.perform(get("/api/book/getAuthors"))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun getPublishers() {
        val expected = listOf(testPublisher1, testPublisher2)
        mockMvc.perform(get("/api/book/getPublishers"))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun create_OK() {
        val json = toCreateFormJsonBy(
                null, "testbook3", 3, "testauthor3", 3, "testpublisher3")
        val expected = BookCreateResponse(listOf())
        mockMvc.perform(
                post("/api/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun create_NG_bookの文字数制限() {
        val json = toCreateFormJsonBy(
                null, "0123456789012345678901", 3, "testauthor3", 3, "testpublisher3")
        val expected = BookCreateResponse(listOf(
                ApiError("title", "タイトルは20文字以内")
        ))
        mockMvc.perform(
                post("/api/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun create_NG_bookのtitleが空() {
        val json = toCreateFormJsonBy(
                null, "", 3, "testauthor3", 3, "testpublisher3")
        val expected = BookCreateResponse(listOf(
                ApiError("title", "タイトルに何か入力してんげり")
        ))
        mockMvc.perform(
                post("/api/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun create_NG_authorのidがnullでnameが空() {
        val json = toCreateFormJsonBy(
                null, "testbook3", null, "", 3, "testpublisher3")
        val expected = BookCreateResponse(listOf(
                ApiError("author.validAuthorInput", "著者に何か入力してんげり")
        ))
        mockMvc.perform(
                post("/api/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun create_NG_publisherのidがnullでnameが空() {
        val json = toCreateFormJsonBy(
                null, "testbook3", 3, "testauthor3", null, "")
        val expected = BookCreateResponse(listOf(
                ApiError("publisher.validPublisherInput", "出版社に何か入力してんげり")
        ))
        mockMvc.perform(
                post("/api/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun create_NG_同じ本がすでに存在している() {
        doReturn(true).whenever(mockBookService).hasSameBook(any())

        val json = toCreateFormJsonBy(
                null, "testbook3", 3, "testauthor3", 3, "testpublisher3")
        val expected = BookCreateResponse(listOf(
                ApiError("duplicated", "この本はすでに存在しています。")
        ))
        mockMvc.perform(
                post("/api/book/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun update_OK() {
        val json = toUpdateFormJsonBy(
                null, "testbook3", 3, "testauthor3", 3, "testpublisher3")
        val expected = BookCreateResponse(listOf())
        mockMvc.perform(
                post("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun update_NG_bookの文字数制限() {
        val json = toUpdateFormJsonBy(
                null, "0123456789012345678901", 3, "testauthor3", 3, "testpublisher3")
        val expected = BookCreateResponse(listOf(
                ApiError("title", "タイトルは20文字以内")
        ))
        mockMvc.perform(
                post("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun update_NG_同じ本がすでに存在している() {
        doReturn(true).whenever(mockBookService).hasSameBook(any())

        val json = toUpdateFormJsonBy(
                null, "testbook3", 3, "testauthor3", 3, "testpublisher3")
        val expected = BookCreateResponse(listOf(
                ApiError("duplicated", "同じ本がすでに存在しています。")
        ))
        mockMvc.perform(
                post("/api/book/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
                .andExpect(content().json(mapper.writeValueAsString(expected)))
    }

    @Test
    fun delete() {
        val json = mapper.writeValueAsString(BookDeleteForm(1))
        mockMvc.perform(
                post("/api/book/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk)
    }

    private fun toCreateFormJsonBy(
            id: Int?, title: String, authorId: Int?, authorName: String, publisherId: Int?, publisherName: String): String {
        val form = BookCreateForm(id, title, AuthorCreateForm(authorId, authorName), PublisherCreateForm(publisherId, publisherName))
        return mapper.writeValueAsString(form)
    }

    private fun toUpdateFormJsonBy(
            id: Int?, title: String, authorId: Int?, authorName: String, publisherId: Int?, publisherName: String): String {
        val form = BookUpdateForm(id, title, AuthorUpdateForm(authorId, authorName), PublisherUpdateForm(publisherId, publisherName))
        return mapper.writeValueAsString(form)
    }

}
