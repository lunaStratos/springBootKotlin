package com.lunastratos.test.TEST.Entities

import com.querydsl.core.annotations.QueryProjection
import javax.persistence.*

@Entity
@Table(name="dept")
data class DeptEntity  @QueryProjection constructor(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: String? = null,
    @Column(name = "sal")
    var sal: Int? = 0,
    @Column(name = "dept")
    var dept: String? = "",
    @Column(name = "dept2")
    var dept2: String? = "",
    @Column(name = "regdate")
    var regdate: String? = ""
    )
