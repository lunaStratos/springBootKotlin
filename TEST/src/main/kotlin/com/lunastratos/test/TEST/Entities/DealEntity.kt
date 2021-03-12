package com.lunastratos.test.TEST.Entities

import com.querydsl.core.annotations.QueryProjection
import javax.persistence.*

@Entity
@Table(name="deal")
data class DealEntity(
    @Id
    @GeneratedValue
    @Column(name = "id")
    var id: String? = null,
    @Column(name = "price")
    var price: Int? = 0,
    @Column(name = "action")
    var action: String? = "",
    @Column(name = "regdate")
    var regdate: String? = ""
    )
