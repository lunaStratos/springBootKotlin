package com.lunastratos.test.TEST.Controller

import com.lunastratos.test.TEST.Service.MemberService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Autowired





@Controller
class MybatisController {

    val logger = LoggerFactory.getLogger(MybatisController::class.java)

    //mybatis 서비스 연결 : lateinit 사용
    // Autowired 사용시 lateinit 사용해야 한다.
    @Autowired
    private lateinit var memberService: MemberService


    //mybatis로 데이터 불러오기
    @ResponseBody
    @GetMapping("/mybatis")
    fun mybatis():String{
        println("===================== mybatis ===========================")
        val cnt = memberService.selectMemberCnt()
        val memberList = memberService.selectMemberList()

        logger.info("cnt: {}", cnt) //int형
        logger.info("memberList: {}", memberList.toString()) // List Map 형태

        return ""+cnt
    }



}