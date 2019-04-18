package com.iijima.bookmanager.infra

import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.dao.PublisherDao
import org.springframework.stereotype.Repository

@Repository
class PublisherRepository (
        private val publisherDao: PublisherDao
){

    fun find(): List<Publisher> {
        val records = publisherDao.select()
        return records.map{ Publisher(it.id, it.name) }
    }

}
