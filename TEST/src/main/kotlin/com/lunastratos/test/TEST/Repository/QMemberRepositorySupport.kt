package com.lunastratos.test.TEST.Repository

import com.lunastratos.test.TEST.Entities.*
import com.querydsl.core.BooleanBuilder
import com.querydsl.core.Tuple
import com.querydsl.core.types.ExpressionUtils
import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.stereotype.Repository
import javax.annotation.Resource
import javax.transaction.Transactional
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.sql.SQLQuery
import com.querydsl.sql.SQLQueryFactory
import com.querydsl.sql.mssql.SQLServerQueryFactory


@Repository
class QMemberRepositorySupport(
    @Resource(name = "jpaQueryFactory")
    val query: JPAQueryFactory
) : QuerydslRepositorySupport(MemberEntity::class.java) {

    /**
     * 참조 : https://joont92.github.io/jpa/QueryDSL/
     */

    //id로 같은 거 찾기 : fetchOne = 1개만
    fun selectMemberById(id: String): MemberEntity? {
        var result: MemberEntity? = query.selectFrom(QMemberEntity.memberEntity)
            .where(QMemberEntity.memberEntity.id.eq(id))
            .fetchOne()
        return result
    }

    /**
     * 일부 컬럼만 뽑아내기, => Projections
     * [{"id":null,"name":"","password":"","age":16,"loc":"","regdate":"","code":""},{"id":null,"name":"","password":"","age":28,"loc":"","regdate":"","code":""},{"id":null,"name":"","password":"","age":38,"loc":"","regdate":"","code":""},{"id":null,"name":"","password":"","age":11,"loc":"","regdate":"","code":""},{"id":null,"name":"","password":"","age":47,"loc":"","regdate":"","code":""}]
     * 로 나온다.
     */
    fun selectByIdselect(): Any? {

        val member = QMemberEntity.memberEntity
        var result = query.select(
            Projections.fields(
                MemberEntity::class.java,
                ExpressionUtils.`as`(member.name, "companyName"),
                member.age)
        ).from(member)
            .fetch()
//            .forEachIndexed {
//                    index, element ->
//                println(index)
//                println(element)
//            } // 검증용

        return result
    }

    /**
     * 일부 컬럼만 뽑아내기
     * [[16, D], [28, C], [38, D], [11, DJ], [47, S]] 로 나온다.
     */
    fun selectByIdselect2(): Any? {

        val member = QMemberEntity.memberEntity
        var result: MutableList<Tuple>? = query.select(
            member.age, member.loc
        ).from(member)
            .fetch()
//            .forEachIndexed {
//                    index, element ->
//                println(index)
//                println(element)
//            } // 검증용

        return result
    }

    /**
     * 같은 나이 찾기
     * eq 는 = 의미
     */
    fun selectByAge(age: Int): List<Any>? {
        var result = query.selectFrom(QMemberEntity.memberEntity)
            .where(QMemberEntity.memberEntity.age.eq(age))
            .fetch()
        return result
    }

    /**
     * 같은 아이디 찾기
     * eq 는 = 의미
     */
    fun selectById(id: String): List<Any>? {
        var result = query.selectFrom(QMemberEntity.memberEntity)
            .where(QMemberEntity.memberEntity.id.eq(id))
            .fetch()
        return result
    }

    /**
     * 같은 이름과 나이 찾기
     */
    fun selectByIdAndName(name: String, age: Int): List<Any>? {
        var result = query.selectFrom(QMemberEntity.memberEntity)
            .where(QMemberEntity.memberEntity.name.eq(name).and(QMemberEntity.memberEntity.age.eq(age)))
            .fetch()
        return result
    }


    /**
     * 나이조회와 지역과 이름을 검색
     * 동적쿼리 처리 방법
     */
    fun selectByNameLocAgeDynamic(name: String, loc: String, startAge: Int, endAge: Int): List<Any>? {
        var build: BooleanBuilder = BooleanBuilder()

        var member = QMemberEntity.memberEntity
        if (name != "") build.and(member.name.eq(name)) //변수 선언후 이런식으로 표현해도 된다.

        if (loc != "") build.and(QMemberEntity.memberEntity.loc.eq(loc))
        // 필수 검색 쿼리
        build.and(QMemberEntity.memberEntity.age.between(startAge, endAge))

        // age.eq(age)              // 나이가 age와 같은거
        // age.between(30, 50);     // 나이가 30~50 사이
        // name.contains("이적");   // '%이적%' 검색
        // name.startsWith("이적"); // '이적%' 검색


        //두가지 방식다 사용가능

        //1
        var result = query.selectFrom(QMemberEntity.memberEntity)
            .where(build).fetch()

        //2
        result = query.selectFrom(member)
            .where(build)
            .fetch()


        return result
    }

    /**
     * 데이터와 페이지 처리
     */
    fun selectMemberPaging(offset: Long, limit: Long): Map<String, Any>? {
        var build: BooleanBuilder = BooleanBuilder()

        var member = QMemberEntity.memberEntity

        //두가지 방식다 사용가능

        //1
        var result = query.selectFrom(QMemberEntity.memberEntity)
            .offset(offset)
            .limit(limit)
            .fetchResults()

        val allResult = result.results // 조회된 Member
        val total = result.total // 전체갯수
        val offset = result.offset // offset : 시작 번호
        val limit = result.limit //limit : 조회할 갯수

        var resultMap = mutableMapOf<String, Any>()
        resultMap.put("allResult", allResult)
        resultMap.put("total", total)
        resultMap.put("offset", offset)
        resultMap.put("limit", limit)

        return resultMap
    }

    /**
     * 조인: 조인 처리 on
     * 특정값 조회시 Projections.fields  사용
     */
    fun selectMemberDeptJoinOn(): Any? {
        var member = QMemberEntity.memberEntity
        var dept = QDeptEntity.deptEntity

        var result = query.select(
            member.id,
            member.name,
            member.age,
            dept.sal
        )
            .from(member)
            .leftJoin(dept)
            .on(dept.id.eq(member.id))
            .fetch()

        return result
    }

    /**
     * 2중조인: 여러개 Join
     * 특정값 조회시 Projections.fields  사용
     */
    fun selectMemberDeptDealCodebookJoinOn(): Any? {
        var member = QMemberEntity.memberEntity
        var dept = QDeptEntity.deptEntity
        var deal = QDealEntity.dealEntity
        var codebook = QCodebookEntity.codebookEntity

        val querys: JPAQuery<*> = JPAQuery<Any?>(entityManager)
        var result = querys.select(
            member.id,
            member.name,
            member.age,
            dept.sal,
            deal.price,
            codebook.code,
            codebook.explain
        )
            .from(member)
            .leftJoin(dept)
            .on(dept.id.eq(member.id))
            .leftJoin(deal)
            .on(deal.id.eq(member.id))
            .leftJoin(codebook)
            .on(codebook.code.eq(member.code))
            //.groupBy(codebook.code)
            .orderBy(codebook.code.asc(), member.age.asc()) //GroupBy시 asc desc() 꼭 해야 함.
            .fetch()
//            .forEachIndexed { index, tuple ->
//                println(tuple.toString())
//            }

        println("result: "+ result)

        return result
    }

    /**
     *  2중조인: 여러개 Join
     * 특정값 조회시 Projections.fields  사용
     */
    fun selectMemberDeptDealCodebookJoinOn2(): Any? {
        var member = QMemberEntity.memberEntity
        var dept = QDeptEntity.deptEntity
        var deal = QDealEntity.dealEntity
        var codebook = QCodebookEntity.codebookEntity

        val querys: JPAQuery<*> = JPAQuery<Any?>(entityManager)
        var result :MutableList<Tuple> = querys.select(
            Projections.fields(
                MemberEntity::class.java,
                ExpressionUtils.`as`(member.name, "companyName"),
                member.age),
            Projections.fields(
                DeptEntity::class.java,
                ExpressionUtils.`as`(dept.sal, "sal"),
                dept.dept)
        )
            .from(member)
            .leftJoin(dept)
            .on(dept.id.eq(member.id))
            .leftJoin(deal)
            .on(deal.id.eq(member.id))
            .leftJoin(codebook)
            .on(codebook.code.eq(member.code))
            //.groupBy(codebook.code)
            .orderBy(codebook.code.asc(), member.age.asc()) //GroupBy시 asc desc() 꼭 해야 함.
            .fetch()
//            .forEachIndexed { index, tuple ->
//                println(tuple.toString())
//            }

        println("result: "+ result)

        return result
    }

    /**
     * 서브쿼리 혹은 스칼라 쓸때
     */
    //서브쿼리 or 스칼라 쓸때
    fun selectMemberByMemberBySal(sal: Int): Any? {
        var member = QMemberEntity.memberEntity
        var dept = QDeptEntity.deptEntity

        var result = query.selectFrom(member)
            .where(
                member.id.`in`(
                    JPAExpressions
                        .select(dept.id)
                        .from(dept)
                        .where(dept.sal.eq(sal))
                )
            )
            .fetch()

        return result
    }


    /**
     * 서브쿼리 or 스칼라 쓸때 , 부등호
     */
    fun selectMemberByMemberBySal2(sal: Int): Any? {
        var member = QMemberEntity.memberEntity
        var dept = QDeptEntity.deptEntity

        var result = query.selectFrom(member)
            .where(
                member.id.`in`(
                    JPAExpressions
                        .select(dept.id)
                        .from(dept)
                        .where(dept.sal.goe(sal))
                )
            )
            .fetch()

        return result
    }


    /**
     * insert 방법
     *  @Transactional, @Modifying
     *  insert delete update의 경우 위의 어노테이션을 추가해야 작동
     */
    @Transactional
    @Modifying
    fun insertMember(maps:Map<String, Any>):Int{
        println(maps.toString())
        var member = QMemberEntity.memberEntity
        var dept = QDeptEntity.deptEntity

        query.insert(member)
            .columns(
                member.id,
                member.name,
                member.password,
                member.age,
                member.loc,
            ).values(
                maps.get("id").toString(),
                maps.get("name").toString(),
                maps.get("password").toString(),
                maps.get("age").toString().toInt(),
                maps.get("loc").toString(),
            ).execute();
        return 1

    }

    /**
     * 업데이트 방법
     *  @Transactional, @Modifying
     *  insert delete update의 경우 위의 어노테이션을 추가해야 작동
     */
    @Transactional
    @Modifying
    fun updateMember(maps:Map<String, Any>){
        println(maps.toString())
        var member = QMemberEntity.memberEntity
        var dept = QDeptEntity.deptEntity

        query.update(member)
            .where(member.id.eq(maps.get("id").toString()))
            .set(member.password, maps.get("password").toString())
            .execute();
    }

    /**
     * 삭제 방법
     *  @Transactional, @Modifying
     *  insert delete update의 경우 위의 어노테이션을 추가해야 작동
     */
    @Transactional
    @Modifying
    fun deleteMember(maps:Map<String, Any>){
        println(maps.toString())
        var member = QMemberEntity.memberEntity
        var dept = QDeptEntity.deptEntity

        query.delete(member)
            .where(member.id.eq(maps.get("id").toString())
                .and(member.name.eq(maps.get("name").toString()))
            )
            .execute()
    }


}