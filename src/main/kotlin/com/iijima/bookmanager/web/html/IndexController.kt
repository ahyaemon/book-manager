package com.iijima.bookmanager.web.html

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping
class IndexController {

    @RequestMapping
    fun index(): String {
        return "index"
    }

}
