plugins {
	java
	id("org.springframework.boot") version "3.1.4"
	id("io.spring.dependency-management") version "1.1.3"
}

group = "com.custom.auth"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.kafka:spring-kafka")
	// https://mvnrepository.com/artifact/org.springframework.cloud/spring-cloud-starter-sleuth
	implementation("org.springframework.cloud:spring-cloud-starter-sleuth:3.1.9")
	// https://mvnrepository.com/artifact/org.springdoc/springdoc-openapi-ui
	implementation("org.springdoc:springdoc-openapi-ui:1.7.0")
	// https://mvnrepository.com/artifact/org.postgresql/postgresql
	implementation("org.postgresql:postgresql:42.6.0")

// https://mvnrepository.com/artifact/org.apache.poi/poi
	implementation("org.apache.poi:poi:5.2.4")
// https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml
	implementation("org.apache.poi:poi-ooxml:5.2.4")

	implementation("io.jsonwebtoken:jjwt-api:0.11.5")
	implementation("org.springframework.boot:spring-boot-starter-security")
	runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
	runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")
	compileOnly("org.projectlombok:lombok")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
//	runtimeOnly("com.h2database:h2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
