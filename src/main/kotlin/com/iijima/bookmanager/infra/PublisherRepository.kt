package com.iijima.bookmanager.infra

import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.dao.PublisherDao
import com.iijima.bookmanager.infra.entity.PublisherRecord
import org.springframework.stereotype.Repository

@Repository
class PublisherRepository (
        private val publisherDao: PublisherDao
){

    /**
     * 全ての [Publisher] を返す。
     */
    fun find(): List<Publisher> {
        val records = publisherDao.select()
        return records.map{ Publisher(it.id, it.name) }
    }

    /**
     * [Publisher] を登録する。
     * 登録した [Publisher] を返す。
     */
    fun save(publisher: Publisher): Publisher {
        val record = PublisherRecord(null, publisher.name)
        val result = publisherDao.insert(record)
        return Publisher(result.entity.id, publisher.name)
    }

}
