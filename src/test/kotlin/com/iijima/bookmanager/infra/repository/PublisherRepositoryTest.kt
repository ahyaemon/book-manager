package com.iijima.bookmanager.infra.repository

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.dao.PublisherDao
import com.iijima.bookmanager.infra.entity.PublisherRecord
import com.nhaarman.mockito_kotlin.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.seasar.doma.jdbc.Result

class PublisherRepositoryTest {

    private val publisherDaoMock = mock<PublisherDao> {
        on { select() } doReturn listOf(
                PublisherRecord(1, "testpublisher1"),
                PublisherRecord(2, "testpublisher2")
        )

        on { insert(any()) } doReturn Result(1, PublisherRecord(1, "testpublisher1"))
    }

    @Test
    fun find() {
        val publisherRepository = PublisherRepository(publisherDaoMock)

        val publishers = publisherRepository.find()

        val publisher = publishers.first{ it.id == 1 }
        assertThat(publisher.name).isEqualTo("testpublisher1")
        verify(publisherDaoMock, times(1)).select()
    }

    @Test
    fun save_キャッシュが無い場合はDaoでINSERT() {
        val publisherRepository = PublisherRepository(publisherDaoMock)

        // キャッシュが無い場合は dao で insert する
        val publisher = Publisher(1, "testpublisher3")
        publisherRepository.save(publisher)
        verify(publisherDaoMock, times(1)).select()
        verify(publisherDaoMock, times(1)).insert(any())
    }

    @Test
    fun save_同名の著者がキャッシュにある場合はDaoが呼ばれない() {
        val publisherRepository = PublisherRepository(publisherDaoMock)

        // キャッシュが無い場合は dao で insert する
        val publisher = Publisher(1, "testpublisher1")
        publisherRepository.save(publisher)
        verify(publisherDaoMock, times(1)).select()
        verify(publisherDaoMock, times(0)).insert(any())
    }

}
