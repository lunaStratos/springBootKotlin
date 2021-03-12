package com.lunastratos.test.TEST.Entities

import javax.persistence.*

/**
 *
 * 컬럼이 reg_time인 경우 camelCase처럼 적어야 _가 붙는다.
 * 예 reg_time인 경우, var regTime: String? = "",
 */
@Entity
@Table(schema = "mydb", name="member") //스키마와 테이블 이름
data class MemberEntity(
    //data class로 선언. 자동으로 equals(), hashCode(), toString, copy(), componentN() 을 생성
    @Id
    @Column(name = "id")
    var id: String? = null,
    @Column(name = "name")
    var name: String? = "",
    @Column(name = "password")
    var password: String? = "",
    @Column(name = "age")
    var age: Int? = 0,
    @Column(name = "loc")
    var loc: String? = "",
    @Column(name = "regdate")
    var regdate: String? = "",
    @Column(name = "code")
    var code: String? = ""
    )
