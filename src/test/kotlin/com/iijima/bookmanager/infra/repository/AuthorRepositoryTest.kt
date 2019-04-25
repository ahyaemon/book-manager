package com.iijima.bookmanager.infra.repository

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.infra.dao.AuthorDao
import com.iijima.bookmanager.infra.entity.AuthorRecord
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.seasar.doma.jdbc.Result

class AuthorRepositoryTest {

    private val authorDaoMock = mock<AuthorDao> {
        on { select() } doReturn listOf(
                AuthorRecord(1, "testauthor1"),
                AuthorRecord(2, "testauthor2")
        )

        on { insert(any()) } doReturn Result(1, AuthorRecord(1, "testauthor1"))
    }

    @Test
    fun find() {
        val authorRepository = AuthorRepository(authorDaoMock)

        val authors = authorRepository.find()

        val author = authors.first{ it.id == 1 }
        assertThat(author.name).isEqualTo("testauthor1")
        verify(authorDaoMock, times(1)).select()
    }

    @Test
    fun save_キャッシュが無い場合はDaoでINSERT() {
        val authorRepository = AuthorRepository(authorDaoMock)

        // キャッシュが無い場合は dao で insert する
        val author = Author(1, "testauthor3")
        authorRepository.save(author)
        verify(authorDaoMock, times(1)).select()
        verify(authorDaoMock, times(1)).insert(any())
    }

    @Test
    fun save_同名の著者がキャッシュにある場合はDaoが呼ばれない() {
        val authorRepository = AuthorRepository(authorDaoMock)

        // キャッシュが無い場合は dao で insert する
        val author = Author(1, "testauthor1")
        authorRepository.save(author)
        verify(authorDaoMock, times(1)).select()
        verify(authorDaoMock, times(0)).insert(any())
    }

}
