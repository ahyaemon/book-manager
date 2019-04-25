package com.iijima.bookmanager.web.api.db

import com.nhaarman.mockito_kotlin.*
import org.flywaydb.core.Flyway
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@RunWith(SpringRunner::class)
@SpringBootTest
class DbControllerTest {

    private val mockFlyway = mock<Flyway> {
        on { migrate() } doReturn 1
    }
    val dbController = DbController(mockFlyway)

    lateinit var mockMvc: MockMvc

    @Before
    fun setup() {
        // mockFlyway 初期化時に void メソッドのモックができなかったため、ここでやる
        doNothing().whenever(mockFlyway).clean()
        mockMvc = MockMvcBuilders.standaloneSetup(dbController).build()
    }

    @Test
    fun init() {
        mockMvc.perform(post("/api/db/init"))
        verify(mockFlyway, times(1)).clean()
        verify(mockFlyway, times(1)).migrate()
    }

}
