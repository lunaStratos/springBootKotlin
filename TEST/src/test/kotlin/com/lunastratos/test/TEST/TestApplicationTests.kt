package com.lunastratos.test.TEST

import com.lunastratos.test.TEST.Entities.MemberEntity
import com.lunastratos.test.TEST.Repository.MemberRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.TestConstructor
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.RequestBuilder
import org.springframework.test.web.servlet.ResultHandler
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders


@SpringBootTest
class TestApplicationTests {

	val logger = LoggerFactory.getLogger(TestApplicationTests::class.java)

	@Test
	fun context1() {
		println("test1!")
		logger.info("logger1")

	}

	@ParameterizedTest
	@ValueSource(ints = [1, 2, 3, 4])
	internal fun `ints values`(value: Int) {
		print("value: $value ") // value: 1 value: 2 value: 3 value: 4
	}

	@Autowired
	protected lateinit var mockMvc: MockMvc // 테스트 요청을 보낼때 사용될 mockMvc

	@Test
	@DisplayName("╯°□°）╯")
	fun testWithDisplayNameContainingSpecialCharacters() {

	}


	@ParameterizedTest
	@ValueSource(strings = ["racecar", "radar", "able was I ere I saw elba"])
	fun palindromes(candidate: String) {
		Assertions.assertTrue(candidate.isNotBlank())
	}




}
