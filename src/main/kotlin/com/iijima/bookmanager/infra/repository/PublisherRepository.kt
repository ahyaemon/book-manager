package com.iijima.bookmanager.infra.repository

import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.dao.PublisherDao
import com.iijima.bookmanager.infra.entity.PublisherRecord
import org.springframework.stereotype.Repository

@Repository
class PublisherRepository (
        private val publisherDao: PublisherDao
){

    /**
     * [find] の初回呼び出し時に DB からデータを取得し、キャッシュする。
     */
    val publishersCache: MutableList<Publisher> by lazy {
        val records = publisherDao.select()
        records.map{ Publisher(it.id, it.name) }.toMutableList()
    }

    /**
     * 全ての [Publisher] を返す。
     */
    fun find(): List<Publisher> = publishersCache

    /**
     * [Publisher] を登録する。
     * 登録した [Publisher] を返す。
     * [Publisher] がすでに存在する場合は登録せずにそのまま返す。
     */
    fun save(publisher: Publisher): Publisher {
        // 存在チェック
        if (publishersCache.any{ it.name == publisher.name }) {
            return publishersCache.first{ it.name == publisher.name }
        }

        // DB に保存
        val record = PublisherRecord(null, publisher.name)
        val result = publisherDao.insert(record)
        val newPublisher = Publisher(result.entity.id, publisher.name)

        // キャッシュに追加
        publishersCache.add(newPublisher)

        return newPublisher
    }

}
