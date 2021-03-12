package com.lunastratos.test.TEST

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.PropertySource

@SpringBootApplication
/**
 * 여러 properties를 적용할때 사용
 * db.properties 로  DB 설정 분리
 */
@PropertySource(
	value = *arrayOf(
		"/properties/common.properties",
		"/properties/common2.properties",
		"/properties/db.properties"
	)
) //여러개 사용시 이렇게 쓴다.

class TestApplication

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
}

