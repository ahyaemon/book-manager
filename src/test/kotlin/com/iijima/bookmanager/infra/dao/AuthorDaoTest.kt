package com.iijima.bookmanager.infra.dao

import com.iijima.bookmanager.infra.entity.AuthorRecord
import org.junit.runner.RunWith
import org.seasar.doma.boot.autoconfigure.DomaAutoConfiguration
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.test.context.jdbc.Sql
import org.springframework.test.context.jdbc.SqlGroup
import org.springframework.test.context.junit4.SpringRunner
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

@RunWith(SpringRunner::class)
@JdbcTest
@Import(DomaAutoConfiguration::class)
        @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@SqlGroup(
    Sql(scripts = ["drop_all_tables.sql"]),
    Sql(scripts = ["classpath:/db/migration/V1.1__create_author.sql"]),
    Sql(scripts = ["author.sql"])
)
@ComponentScan
class AuthorDaoTest {

    @Autowired
    lateinit var authorDao: AuthorDao

    @Test
    fun select() {
        val authorRecords = authorDao.select()
        assertThat(authorRecords.size, `is`(2))

        val authorRecord1 = authorRecords.first{ it.id == 1 }
        assertThat(authorRecord1.name, `is`("testman1"))
    }

    @Test
    fun insert() {
        val record = AuthorRecord(null, "testman3")
        authorDao.insert(record)

        val actual = authorDao.select().first{ it.name == "testman3" }
        assertThat(actual.id, `is`(1001))

    }

}
