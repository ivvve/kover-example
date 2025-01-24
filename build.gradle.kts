import kotlinx.kover.gradle.plugin.dsl.CoverageUnit

plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id("org.jetbrains.kotlinx.kover") version "0.9.1"
}

group = "com.tistory.devs0n"
version = "0.0.1-SNAPSHOT"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

kover.reports {
    verify {
        rule("Branch coverage") {
            bound {
                minValue.set(0)
                coverageUnits.set(CoverageUnit.BRANCH)
            }
        }
        rule("Line coverage") {
            bound {
                minValue.set(18)
                coverageUnits.set(CoverageUnit.LINE)
            }
        }
        rule("Instruction coverage") {
            bound {
                minValue.set(15)
                coverageUnits.set(CoverageUnit.INSTRUCTION)
            }
        }
    }
}
