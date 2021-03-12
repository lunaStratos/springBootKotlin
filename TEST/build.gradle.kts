import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.4.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	kotlin("jvm") version "1.4.30"
	kotlin("plugin.spring") version "1.4.30"
	//JPA 추가
	kotlin("plugin.jpa") version "1.4.30"
	//QueryDSL 추가
	id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
	id("java")
	kotlin("kapt") version "1.4.10"
}

group = "com.lunastratos.test"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-mustache")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	//JSP 엔진 역할
	implementation ("org.apache.tomcat.embed:tomcat-embed-jasper")
	implementation("javax.servlet:jstl:1.2")

	//MariaDB
	implementation("org.mariadb.jdbc:mariadb-java-client:2.7.0")
	//Mysql
	implementation("mysql:mysql-connector-java")

	// QueryDSL1
	implementation("com.querydsl:querydsl-jpa")
	kapt("com.querydsl:querydsl-apt::jpa")

	// QueryDSL2
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
	annotationProcessor(group = "com.querydsl", name = "querydsl-apt", classifier = "jpa")

	// https://mvnrepository.com/artifact/com.querydsl/querydsl-sql
	implementation("com.querydsl:querydsl-sql:4.4.0")
	// mybatis
	implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")
	
	//타임리프
	implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")


}

//QueryDSL4 경로 추가
sourceSets["main"].withConvention(org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet::class){
	kotlin.srcDir("$buildDir/generated/source/kapt/main")
}


tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}


