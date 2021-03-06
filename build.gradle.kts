import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.5.2"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.graalvm.buildtools.native") version "0.9.0"

	kotlin("jvm") version "1.5.20"
	kotlin("plugin.spring") version "1.5.20"
}

group = "com.tootest.ch"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	mavenCentral()
	maven { url = uri("https://repo.spring.io/release") }
	maven { url = uri("https://jitpack.io") }
	maven {
		url = uri("https://maven.pkg.github.com/exxonmobil/chemit_occ_spring_tracer")
		credentials {
			username = System.getenv("MAVEN_USERNAME")
			password = System.getenv("MAVEN_PASSWORD")
		}
	}
}

dependencies {
	implementation ("com.xom.chem.china:spring-boot-starter-occ-tracer:4.0.3")
	// Jaeger
	implementation ("io.opentracing.contrib:opentracing-spring-cloud-starter:0.5.9")
	implementation ("io.jaegertracing:jaeger-client:1.6.0")
	// khttp
	implementation ("com.github.jkcclemens:khttp:0.1.0")
	// Spring
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
