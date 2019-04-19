package com.iijima.bookmanager.env.schecule

import org.quartz.Job
import org.quartz.JobExecutionContext
import org.slf4j.LoggerFactory
import java.net.HttpURLConnection
import java.net.URL

class SelfAccessJob: Job {

    private val logger = LoggerFactory.getLogger(SelfAccessJob::class.java)

    /**
     * heroku を落とさないために自分自身にアクセスする Job。
     */
    override fun execute(context: JobExecutionContext){
        val url = URL("https://iijima-book-manager-1234567890.herokuapp.com/")
        val con = url.openConnection() as HttpURLConnection
        con.requestMethod = "GET"
        con.connect()
        val statusCode = con.responseCode
        logger.info("self access: $statusCode")
    }

}
