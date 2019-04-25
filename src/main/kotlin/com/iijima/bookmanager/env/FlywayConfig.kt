package com.iijima.bookmanager.env

import org.flywaydb.core.Flyway
import org.flywaydb.core.api.FlywayException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
class FlywayConfig (
        private val dataSource: DataSource
) {

    private val logger = LoggerFactory.getLogger(FlywayConfig::class.java)

    /**
     * DBの初期化はここで行う（起動時の自動 Migration はやらない）
     * flywayのバージョンに変更があった場合のみ実行
     * ・clean
     * ・migrate
     */
    @Autowired
    fun initDB(){
        val flyway = flyway()
        if (!flyway.updated()) {
            logger.info("Skipping flyway clean and migration.")
            return
        }

        flyway.clean()
        flyway.migrate()
    }

    @Bean
    fun flyway(): Flyway {
        val flyway = Flyway.configure().load()
        flyway.dataSource = dataSource
        return flyway
    }

    private fun Flyway.updated(): Boolean{
        try{
            validate()
        }
        catch(e: FlywayException){
            return true
        }
        return false
    }

}