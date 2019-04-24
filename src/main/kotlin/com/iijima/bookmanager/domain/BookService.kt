package com.iijima.bookmanager.domain

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import com.iijima.bookmanager.infra.repository.AuthorRepository
import com.iijima.bookmanager.infra.repository.BookRepository
import com.iijima.bookmanager.infra.repository.PublisherRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class BookService (
        private val bookRepository: BookRepository,
        private val authorRepository: AuthorRepository,
        private val publisherRepository: PublisherRepository
) {

    /**
     * [Book] を検索して返す。
     */
    fun find(): List<Book> = bookRepository.find()

    /**
     * 条件付きで [Book] を検索して返す。
     */
    fun findBy(title: String, authorId: Int?, publisherId: Int?): List<Book> =
            bookRepository.findBy(title, authorId, publisherId)

    /**
     * [Author] を検索して返す。
     */
    fun findAuthors(): List<Author> = authorRepository.find()

    /**
     * [Publisher] を検索して返す。
     */
    fun findPublishers(): List<Publisher> = publisherRepository.find()

    /**
     * [Book] を新規登録する。
     * [Author] が存在しない場合は [Author] も新規登録する。
     * [Publisher] が存在しない場合は [Publisher] も新規登録する。
     */
    fun save(book: Book) {
        // author の id が　null の場合は新規登録とみなす。
        val author = if (book.author.id == null) {
            authorRepository.save(book.author)
        } else {
            book.author
        }

        // publisher の id が　null の場合は新規登録とみなす。
        val publisher = if (book.publisher.id == null) {
            publisherRepository.save(book.publisher)
        } else {
            book.publisher
        }

        bookRepository.save(Book(null, book.title, author, publisher))
    }

    /**
     * [Book] を新規登録する。
     * [Author] が存在しない場合は [Author] を新規登録する。
     * [Publisher] が存在しない場合は [Publisher] を新規登録する。
     */
    fun update(book: Book) {
        // author の id が　null の場合は新規登録とみなす。
        val author = if (book.author.id == null) {
            authorRepository.save(book.author)
        } else {
            book.author
        }

        // publisher の id が　null の場合は新規登録とみなす。
        val publisher = if (book.publisher.id == null) {
            publisherRepository.save(book.publisher)
        } else {
            book.publisher

        }
        bookRepository.update(Book(book.id, book.title, author, publisher))
    }

    /**
     * [Book] を削除する。
     */
    fun delete(id: Int) {
        bookRepository.delete(id)
    }

    /**
     * 異なる id でタイトル、著者、出版社が重複する [Book] が存在する場合、true。
     */
    fun hasSameBook(book: Book): Boolean = bookRepository.countSameBook(book) > 0

}
