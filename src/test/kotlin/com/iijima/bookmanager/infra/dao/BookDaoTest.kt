package com.iijima.bookmanager.infra.dao

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.entity.BookRecord
import org.assertj.core.api.Assertions.assertThat
import org.junit.runner.RunWith
import org.seasar.doma.boot.autoconfigure.DomaAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.junit4.SpringRunner
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

@RunWith(SpringRunner::class)
@JdbcTest
@Import(DomaAutoConfiguration::class)
        @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SqlGroup(
    Sql(scripts = ["drop_all_tables.sql"]),
    Sql(scripts = ["classpath:/db/migration/V1.1__create_author.sql"]),
    Sql(scripts = ["classpath:/db/migration/V2.1__create_publisher.sql"]),
    Sql(scripts = ["classpath:/db/migration/V3.1__create_book.sql"]),
    Sql(scripts = ["book.sql"])
)
@ComponentScan
class BookDaoTest {

    @Autowired
    lateinit var bookDao: BookDao

    @Test
    fun selectBookSummaries() {
        val bookSummaries = bookDao.selectBookSummaries()

        val bookSummary = bookSummaries.first{ it.id == 1 }
        assertThat(bookSummary.title).isEqualTo("testbook1")
        assertThat(bookSummary.authorName).isEqualTo("testman1")
        assertThat(bookSummary.publisherName).isEqualTo("testpublisher1")
    }

    @Test
    fun selectBookSummariesBy() {
        val bookSummaries1 =
                bookDao.selectBookSummariesBy(null, null, null)
        val bookSummary1 = bookSummaries1.first{ it.id == 1 }
        assertThat(bookSummary1.title).isEqualTo("testbook1")

        val bookSummaries2 =
                bookDao.selectBookSummariesBy("testbook1", null, null)
        val bookSummary2 = bookSummaries2.first{ it.id == 1 }
        assertThat(bookSummary2.title).isEqualTo("testbook1")

        val bookSummaries3 =
                bookDao.selectBookSummariesBy(null, 2, null)
        val bookSummary3 = bookSummaries3.first{ it.id == 2 }
        assertThat(bookSummary3.title).isEqualTo("testbook2")

        val bookSummaries4 =
                bookDao.selectBookSummariesBy(null, null, 2)
        val bookSummary4 = bookSummaries4.first{ it.id == 2 }
        assertThat(bookSummary4.title).isEqualTo("testbook2")
    }

    @Test
    fun countSameBook() {
        // エンティティとして同じ本(idが同一)はカウントしない
        val author1 = Author(1, "testman1")
        val publisher1 = Publisher(1, "testpublisher1")
        val book1 = Book(1, "testbook1", author1, publisher1)
        val count1 = bookDao.countSameBook(book1)
        assertThat(count1).isEqualTo(0)

        val author2 = Author(1, "testman1")
        val publisher2 = Publisher(1, "testpublisher1")
        val book2 = Book(2, "testbook1", author2, publisher2)
        val count2 = bookDao.countSameBook(book2)
        assertThat(count2).isEqualTo(1)

        val author3 = Author(999, "xxx")
        val publisher3 = Publisher(999, "xxx")
        val book3 = Book(999, "xxx", author3, publisher3)
        val count3 = bookDao.countSameBook(book3)
        assertThat(count3).isEqualTo(0)
    }

    @Test
    fun insert() {
        val bookRecord = BookRecord(null, "testbook3", 1, 1)
        bookDao.insert(bookRecord)

        val actual = bookDao.selectBookSummaries().first{ it.title == "testbook3"}
        assertThat(actual.id).isEqualTo(1001)
    }

    @Test
    fun update() {
        val bookRecord = BookRecord(1, "testbook_updated", 2, 2)
        bookDao.update(bookRecord)

        val actual = bookDao.selectBookSummaries().first{ it.id == 1}
        assertThat(actual.title).isEqualTo("testbook_updated")
        assertThat(actual.authorId).isEqualTo(2)
        assertThat(actual.publisherId).isEqualTo(2)
    }

    @Test
    fun delete() {
        bookDao.delete(1)
        val bookSummaries =
                bookDao.selectBookSummariesBy("testbook1", null, null)
        assertThat(bookSummaries.size).isEqualTo(0)
    }

}
