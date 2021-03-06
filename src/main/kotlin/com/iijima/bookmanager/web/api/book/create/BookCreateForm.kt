package com.iijima.bookmanager.web.api.book.create

import com.iijima.bookmanager.domain.entity.Author
import com.iijima.bookmanager.domain.entity.Book
import com.iijima.bookmanager.domain.entity.Publisher
import javax.validation.Valid
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class BookCreateForm (
        val id: Int?,

        @field:NotEmpty(message="タイトルに何か入力してんげり")
        @field:Size(max=20, message="タイトルは20文字以内")
        val title: String,

        @field:Valid
        val author: AuthorCreateForm,

        @field:Valid
        val publisher: PublisherCreateForm
) {
        fun toBook(): Book = Book(
                id, title, Author(author.id, author.name), Publisher(publisher.id, publisher.name))

}
