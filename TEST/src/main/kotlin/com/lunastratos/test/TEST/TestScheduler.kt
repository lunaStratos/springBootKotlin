package com.lunastratos.test.TEST

import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*

/**
 * 스케쥴링 하는 방법입니다.
 * 배치로 도는 메소드 실행
 */
@Component
@EnableScheduling //스케쥴링
class TestScheduler {
    
    //로그찍기 
    val logger = LoggerFactory.getLogger(TestScheduler::class.java)

    //초 분 시 일 월 요일
    // 05시에 실행되는 배치
    @Scheduled(cron="0 0 05 * * *")
    fun TestScheduler(){
        val msg = "5시 실행 배치입니다."
        println(msg)
        logger.info(msg)
    }

    //60초마다 실행
    @Scheduled(fixedDelay = 60000)
    fun testTime(){
        val dateTime = Calendar.getInstance().time
        println("실행시간 : " + dateTime)
    }

}