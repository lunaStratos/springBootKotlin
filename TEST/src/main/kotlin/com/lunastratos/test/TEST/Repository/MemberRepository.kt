package com.lunastratos.test.TEST.Repository

import org.springframework.stereotype.Repository
import com.lunastratos.test.TEST.Entities.*
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param


/**
 * jpa와 JPQL 방식의 쿼리들.
 * 현업에서는 select는 일반조회외엔 잘 안쓴다.
 * save나 delete등은 추상화 되있다.
 *
 *
 * CrudRepository는 관리되는 엔티티 클래스에 대해 정교한 CRUD 기능을 제공한다.
 *
 */

@Repository
interface MemberRepository:JpaRepository<MemberEntity, String>{

    // 임의쿼리(JPA문법에 맞게...) : id와 name으로 찾는 방식
    /**
     * 출력쿼리
     * select
        memberenti0_.id as id1_0_,
        memberenti0_.age as age2_0_,
        memberenti0_.code as code3_0_,
        memberenti0_.loc as loc4_0_,
        memberenti0_.name as name5_0_,
        memberenti0_.password as password6_0_,
        memberenti0_.regdate as regdate7_0_
        from
        member memberenti0_
        where
        memberenti0_.id=?
        and memberenti0_.name=?
     */
    fun findByIdAndName(id:String, name:String):Any?

    /**
     * JPQL 방식으로 조회하는 방법
     * @Param("name") 을 두면  :name으로 파라메터를 줄수 있다.
     */
    @Query("SELECT id, name FROM member where name = :name", nativeQuery = true)
    fun selectJpqlIdName(@Param("name") name: String): Any


}
