package com.lunastratos.test.TEST.Controller

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

/*
* 스프링부트 + 코틀린 모드에서 쿠키와 세션 동작 방법 설명 컨트롤러
* */

@Controller
class HomeController (){

    val logger = LoggerFactory.getLogger(HomeController::class.java)


    // propertise 부르는 방법
    @Value("\${data.alpha}")
    lateinit var alpha:String

    @Value("\${data.bravo}")
    lateinit var bravo:String

    @Value("\${data.charlie}")
    lateinit var charlie:String

    @Value("\${bravo.alpha}")
    lateinit var bravoAlpha:String
    
    //시작
    @RequestMapping("/")
    fun index():String{
        logger.info("Welcome index")
        return "index"
    }

    @RequestMapping("/thymeleafTest")
    fun thymeleafTest( model:Model):String {
        model.addAttribute("testModel", "testModel")
        return "thymeleafTest";
    }



    @GetMapping("/login")
    fun login(model:Model):String{
        return "login"
    }


    // session, Cookie Add 방법
    @PostMapping("/login")
    fun login(model:Model, id:String, password:String,
              session:HttpSession, cookie:HttpServletResponse):String{

        session.setAttribute("id", id)
        model.addAttribute("id", id)
        val cookieId = Cookie("id", id)
        val cookiePassword = Cookie("pw", password)
        cookie.addCookie(cookieId)
        cookie.addCookie(cookiePassword)

        return "login"
    }

    // cookie와 session 확인 값 얻는 방법
    @GetMapping("/sessionCookie")
    fun sessionCookie(model:Model,
                      session:HttpSession, request:HttpServletRequest):String{

        val cookies = request.cookies
        var resultId = ""


        //for ( str in cookies){
        for ((index, str) in cookies.withIndex()){
            println(str.name + " / " + str.value + " / " + index )
            if(str.name == "id")  resultId = str.value
        }

        model.addAttribute("idCookie", resultId)
        model.addAttribute("idSession", session.getAttribute("id"))
        return "sessionCookie"
    }


    //프로퍼티 부르기
    @ResponseBody
    @GetMapping("/test")
    fun test():String{
        val returnValue = "common.properties : "+ alpha + " / " + bravo + " / " + charlie + " / " + bravoAlpha

        logger.debug("===================== test ===========================")
        logger.debug(returnValue)

        return returnValue
    }


}