package com.iijima.bookmanager.web.api.book.update

import javax.validation.constraints.AssertTrue

data class AuthorUpdateForm (
        val id: Int?,

        val name: String
) {

    @AssertTrue(message = "著者に何か入力してんげり")
    fun isValidAuthorInput(): Boolean {
        return (id != null) || (name.isNotEmpty())
    }

}
