package com.lunastratos.test.TEST.Controller

import com.lunastratos.test.TEST.Entities.MemberEntity
import com.lunastratos.test.TEST.Repository.MemberRepository
import com.lunastratos.test.TEST.Repository.QMemberRepositorySupport
import com.querydsl.core.Tuple
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * QueryDSL 방식으로 다루는 방법 모음 컨트롤러
 */

@Controller
class SQLController (
    val memberRepository: MemberRepository,
    val memberRepositorySupport: QMemberRepositorySupport){


    /**
     * id로 조회
     */
    @ResponseBody
    @GetMapping("/selectMemberById")
    fun selectByIdselect(model:Model, @RequestParam id:String):ResponseEntity<Any>{
        val result = memberRepositorySupport.selectMemberById(id)
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * 일부 컬럼만 뽑아내기
     * Projections.fields 사용
     */
    @ResponseBody
    @GetMapping("/selectByIdselect")
    fun selectByIdselect(model:Model):ResponseEntity<Any>{
        val result = memberRepositorySupport.selectByIdselect()
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * 일부 컬럼만 뽑아내기 2
     * 직접 컬럼이름 적음.
     */
    @ResponseBody
    @GetMapping("/selectByIdselect2")
    fun selectByIdselect2(model:Model):ResponseEntity<Any>{
        val list  = memberRepositorySupport.selectByIdselect2()
        println("query result : " + list.toString())

        return ResponseEntity<Any>(list, HttpStatus.OK)
    }

    /**
     * 같은 나이 찾기
     */
    @ResponseBody
    @GetMapping("/selectByAge")
    fun selectByAge(model:Model, @RequestParam age:Int):ResponseEntity<Any>{
        val result = memberRepositorySupport.selectByAge(age)
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }


    /**
     * 같은 아이디 찾기
     */
    @ResponseBody
    @GetMapping("/selectById")
    fun selectById(model:Model, @RequestParam id:String):ResponseEntity<Any>{
        val result = memberRepositorySupport.selectById(id)
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * 같은 이름과 나이 찾기
     */
    @ResponseBody
    @GetMapping("/selectByIdAndName")
    fun selectByIdAndName(model:Model, @RequestParam name:String, @RequestParam age:Int):ResponseEntity<Any>{
        val result = memberRepositorySupport.selectByIdAndName(name, age)
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * 나이조회와 지역과 이름을 검색
     * 나이는 필수값
     * 지역과 이름은 없어도 동작
     * 동적쿼리
     */
    @ResponseBody
    @GetMapping("/selectByNameLocAgeDynamic")
    fun selectByNameLocAgeDynamic(model:Model,   @RequestParam(required = false, defaultValue = "") name:String
                              , @RequestParam(required = false, defaultValue = "") loc:String
                              , @RequestParam startAge:Int
                              , @RequestParam endAge:Int):ResponseEntity<Any>{
        val result = memberRepositorySupport.selectByNameLocAgeDynamic(name, loc, startAge, endAge)
        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * 데이터와 페이지 처리
     */
    @ResponseBody
    @GetMapping("/selectMemberPaging")
    fun selectMemberPaging(model:Model, offset:Long
                              , limit:Long):ResponseEntity<Any>{
        val result = memberRepositorySupport.selectMemberPaging(offset, limit)
        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * 조인: 조인 처리 on
     * 특정값 조회시 Projections.fields  사용
     */
    @ResponseBody
    @GetMapping("/selectMemberDeptJoinOn")
    fun selectMemberDeptJoinOn():ResponseEntity<Any>{
        val result = memberRepositorySupport.selectMemberDeptJoinOn()
        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * 2중조인: 야러개 Join
     * 특정값 조회시 Projections.fields  사용
     */
    @ResponseBody
    @GetMapping("/selectMemberDeptDealCodebookJoinOn")
    fun selectMemberDeptDealCodebookJoinOn():ResponseEntity<Any>{
        val result = memberRepositorySupport.selectMemberDeptDealCodebookJoinOn()

        return ResponseEntity<Any>(result.toString(), HttpStatus.OK)
    }

    /**
     *  2중조인: 여러개 Join
     * 특정값 조회시 Projections.fields  사용
     */
    @ResponseBody
    @GetMapping("/selectMemberDeptDealCodebookJoinOn2")
    fun selectMemberDeptDealCodebookJoinOn2():ResponseEntity<Any>{
        val result:MutableList<Tuple> = memberRepositorySupport.selectMemberDeptDealCodebookJoinOn2() as MutableList<Tuple>
        println(result.toString())
        for ((index, str)  in result.withIndex()){
            println(""+ str + " / " + index)
        }
        return ResponseEntity<Any>(result.toString(), HttpStatus.OK)
    }


    /**
     * 서브쿼리 혹은 스칼라 쓸때
     */
    @ResponseBody
    @GetMapping("/selectMemberByMemberBySal")
    fun selectMemberByMemberBySal(sal:Int):ResponseEntity<Any>{
        val result = memberRepositorySupport.selectMemberByMemberBySal(sal)
        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * 서브쿼리 or 스칼라 쓸때 , 부등호
     */
    @ResponseBody
    @GetMapping("/selectMemberByMemberBySal2")
    fun selectMemberByMemberBySal2(sal:Int):ResponseEntity<Any>{
        val result = memberRepositorySupport.selectMemberByMemberBySal2(sal)
        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * insert 방법
     */
    @ResponseBody
    @GetMapping("/insertMember")
    fun insertMember(id:String, name:String, password:String, age:Int, loc:String):ResponseEntity<Any>{
        var param = mutableMapOf<String, Any>()
        param.put("name", name)
        param.put("password", password)
        param.put("id", id)
        param.put("age", age)
        param.put("loc", loc)

        val result = memberRepositorySupport.insertMember(param)
        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }


    /**
     * 업데이트 방법
     */
    @ResponseBody
    @GetMapping("/updateMember")
    fun updateMember(id:String, password:String):ResponseEntity<Any>{
        var param = mutableMapOf<String, Any>()
        param.put("password", password)
        param.put("id", id)

        val result = memberRepositorySupport.updateMember(param)
        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }

    /**
     * delete 방법
     */
    @ResponseBody
    @GetMapping("/deleteMember")
    fun deleteMember(id:String, name:String):ResponseEntity<Any>{
        var param = mutableMapOf<String, Any>()
        param.put("id", id)
        param.put("name", name)

        val result = memberRepositorySupport.deleteMember(param)
        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }




    /**
     * save, saveAll은 insert와 update 역할을 한다
     * id값으로 있으면 update를 한다.
     */
    @ResponseBody
    @GetMapping("/insertDuplicateOnUpdate")
    fun insertDuplicateOnUpdate():ResponseEntity<Any>{
        var param = mutableListOf<MemberEntity>()

        val str = "dataname"
        for(strs in 0 .. 10 ){
            val data = MemberEntity(
                str + strs
                , "dataname09"
                , "dataname09"
                , 20
                , "dataname09"
                ,null
                , "dataname09"
            )
            param.add(data)

        }

        val result = memberRepository.saveAll(param)

        println(result.toString())
        return ResponseEntity<Any>(result, HttpStatus.OK)
    }


}