package com.iijima.bookmanager.env.schecule

import org.quartz.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("production")
class SchedulerConfig (
        private val scheduler: Scheduler
) {

    /**
     * 20分毎に SelfAccessJob を実行する。
     */
    @Autowired
    fun regularAccessSchedule() {
        val jobDetail: JobDetail = JobBuilder
                .newJob(SelfAccessJob::class.java)
                .withIdentity("testJob", "group1")
                .build()
        val trigger: Trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/20 * * * ?"))
                .build()
        scheduler.scheduleJob(jobDetail, trigger)
        scheduler.start()
    }

}
