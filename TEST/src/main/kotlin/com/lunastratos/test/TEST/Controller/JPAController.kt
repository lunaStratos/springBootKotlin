package com.lunastratos.test.TEST.Controller

import com.lunastratos.test.TEST.Entities.MemberEntity
import com.lunastratos.test.TEST.Repository.MemberRepository
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * JPA 방식을 사용하는 기능들 모음
 */

@Controller
class JPAController (val memberRepository: MemberRepository
                    ){

    val logger = LoggerFactory.getLogger(JPAController::class.java)

    /**
     * 타임리프 간단문법 적용 html
     */
    @GetMapping(path=["/timeleaf"])
    fun timeleaf(model:Model):String{
        val result = memberRepository.findAll()
        model.addAttribute("data", result)
        model.addAttribute("textData", "컨트롤러에서 가벼온 텍스트데이터")
        model.addAttribute("status", "COMPLETE")

        return "timeleaf"
    }

    /**
     * 일반 조회
     */
    @ResponseBody
    @GetMapping(path=["/getAll"])
    fun getAll(model:Model):ResponseEntity<Any>{
        val result = memberRepository.findAll()
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * Repository에서 임의로 정의한 조회
     */
    @ResponseBody
    @GetMapping(path=["/getByIdAndName"])
    fun getByIdAndName(
        model:Model,
        @RequestParam id:String,
        @RequestParam name:String):ResponseEntity<Any>{
        val result = memberRepository.findByIdAndName(id, name)
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * JPQL 방식으로 조회하는 방법
     * 현업에서는 잘 안쓴다. (QueryDSL이 있기 때문에 )
     */
    @ResponseBody
    @GetMapping("/selectQueryIdName")
    fun selectQueryIdName(model:Model, @RequestParam name:String):ResponseEntity<Any>{
        logger.debug("name : {} ", name)

        val result = memberRepository.selectJpqlIdName(name)
        logger.debug(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }


    /**
     * jpa 형태로 저장 방법
     */
    @ResponseBody
    @GetMapping("/insertSave")
    fun insertSave(id:String, name:String, password:String, age:Int, loc:String):ResponseEntity<Any>{
        var param = mutableMapOf<String, Any>()
        param.put("name", name)
        param.put("password", password)
        param.put("id", id)
        param.put("age", age)
        param.put("loc", loc)

        val mem = MemberEntity(
            id,
            name,
            password,
            age,
            loc,
            null
        )
        val result = memberRepository.save(mem)

        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }


    /**
     * jpa 형태로 List 저장방법
     */
    @ResponseBody
    @GetMapping("/insertSaveList")
    fun insertSaveList():ResponseEntity<Any>{
        var param = mutableListOf<MemberEntity>()

        val str = "dataname"
        for(strs in 0 .. 10 ){
            val data = MemberEntity(
                str + strs
                , str + strs
                , str + strs
                , strs
                , str + strs
                ,null
                , "s"
            )
            param.add(data)

        }

        val result = memberRepository.saveAll(param)

        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }


}