package com.lunastratos.test.TEST.Mapper

import org.apache.ibatis.annotations.Mapper
import org.springframework.stereotype.Repository

//Repository & Mapper μ μΈνμ
@Repository
@Mapper
interface MybatisMemberMapper {
    fun selectMemberCnt() : Int
    fun selectMemberList(): List<Map<String, Any>>
}