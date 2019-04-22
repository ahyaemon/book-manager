package com.iijima.bookmanager.web.api.book.create

import javax.validation.constraints.AssertTrue

data class PublisherCreateForm (
        val id: Int?,

        val name: String
) {

    @AssertTrue(message = "出版社に何か入力してんげり")
    fun isValidPublisherInput(): Boolean {
        return (id != null) || (name.isNotEmpty())
    }

}