import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	war
	kotlin("jvm") version "1.8.22"
}

group = "io.github.funch"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
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
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

	// https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-dynamodb
	implementation("com.amazonaws:aws-java-sdk-dynamodb:1.12.592")

}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
