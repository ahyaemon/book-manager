package com.iijima.bookmanager.web.api.book.update

import com.iijima.bookmanager.domain.entity.Book
import javax.validation.constraints.AssertTrue

data class PublisherUpdateForm (
        val id: Int?,

        val name: String
) {

    @AssertTrue(message = "出版社に何か入力してんげり")
    fun isValidPublisherInput(): Boolean {
        return (id != null) || (name.isNotEmpty())
    }

}
