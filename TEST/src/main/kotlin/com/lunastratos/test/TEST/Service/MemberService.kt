package com.lunastratos.test.TEST.Service

import com.lunastratos.test.TEST.Mapper.MybatisMemberMapper
import org.springframework.stereotype.Service
import org.springframework.beans.factory.annotation.Autowired




@Service
class MemberService {

    @Autowired
    private lateinit var mybatisMemberMapper: MybatisMemberMapper

    fun selectMemberCnt(): Int? {
        return mybatisMemberMapper.selectMemberCnt()
    }

    fun selectMemberList(): List<Map<String, Any>>? {
        return mybatisMemberMapper.selectMemberList()
    }


}