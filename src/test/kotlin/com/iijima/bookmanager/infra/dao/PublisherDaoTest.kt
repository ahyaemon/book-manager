package com.iijima.bookmanager.infra.dao

import com.iijima.bookmanager.infra.entity.PublisherRecord
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
    Sql(scripts = ["classpath:/db/migration/V2.1__create_publisher.sql"]),
    Sql(scripts = ["publisher.sql"])
)
@ComponentScan
class PublisherDaoTest {

    @Autowired
    lateinit var publisherDao: PublisherDao

    @Test
    fun select() {
        val publisherRecords = publisherDao.select()
        assertThat(publisherRecords.size).isEqualTo(2)

        val publisherRecord1 = publisherRecords.first{ it.id == 1 }
        assertThat(publisherRecord1.name).isEqualTo("testpublisher1")
    }

    @Test
    fun insert() {
        val publisherRecord = PublisherRecord(null, "testpublisher3")
        publisherDao.insert(publisherRecord)

        val actual = publisherDao.select().first{ it.name == "testpublisher3" }
        assertThat(actual.id).isEqualTo(1001)
    }

}
