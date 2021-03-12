package com.lunastratos.test.TEST.Entities

import com.querydsl.core.annotations.QueryProjection
import javax.persistence.*

@Entity
@Table(name="codebook")
data class CodebookEntity(
    @Id
    @Column(name = "code")
    var code: String? = null,
    @Column(name = "explain")
    var explain: String? = ""
    )
