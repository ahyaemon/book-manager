package com.iijima.bookmanager.infra

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.infra.dao.AuthorDao
import com.iijima.bookmanager.infra.entity.AuthorRecord
import org.springframework.stereotype.Repository

@Repository
class AuthorRepository (
        private val authorDao: AuthorDao
){
    /**
     * [find] の初回呼び出し時に DB からデータを取得し、キャッシュする。
     */
    val authorsCache: MutableList<Author> by lazy {
        val records = authorDao.select()
        records.map{ Author(it.id, it.name) }.toMutableList()
    }

    /**
     * 全ての [Author] を返す。
     */
    fun find(): List<Author> = authorsCache

    /**
     * [Author] を登録する。
     * 登録した [Author] を返す。
     * [Author] がすでに存在する場合は登録せずにそのまま返す。
     */
    fun save(author: Author): Author {
        // 存在チェック
        if (authorsCache.any { it.name == author.name }) {
            return authorsCache.first{ it.name == author.name }
        }

        // DB に保存
        val record = AuthorRecord(null, author.name)
        val result = authorDao.insert(record)
        val newAuthor = Author(result.entity.id, author.name)

        // キャッシュに追加
        authorsCache.add(newAuthor)

        return newAuthor
    }

 }
