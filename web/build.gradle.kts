import org.springframework.boot.gradle.tasks.run.BootRun

plugins {
    java
    id("org.springframework.boot") version "3.4.2"
    id("io.spring.dependency-management") version "1.1.7"
    id ("com.diffplug.spotless") version "7.0.2"
    id("org.sonarqube") version "6.0.1.5171"
    id("org.jetbrains.kotlin.jvm") version "2.1.10"
    id ("java")
    id ("jacoco")
}

val springdocVersion = "2.8.4"
val freeMarkerVersion = "2.3.34"
val jmsApiVersion = "2.0.1"
val mockitoKotlinVersion = "5.4.0"

group = "com.josdem.jmailer"
version = "1.4.12"


java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

kotlin {
    jvmToolchain(21)
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

sonar {
    properties {
        property("sonar.projectKey", "josdem_jmailer-spring-boot")
        property("sonar.organization", "josdem-io")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

spotless {
    java {
        googleJavaFormat()
        removeUnusedImports()
        endWithNewline()
    }
    kotlin {
        target("**/*.kt")
        targetExclude("**/build/**", "**/build-*/**")
        ktlint()
        trimTrailingWhitespace()
        endWithNewline()
    }
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-aop")
    implementation ("org.springframework.boot:spring-boot-starter-mail")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-activemq")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("javax.jms:javax.jms-api:$jmsApiVersion")
    implementation ("org.freemarker:freemarker:$freeMarkerVersion")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")
    implementation (project (":services"))
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    testCompileOnly ("org.projectlombok:lombok")
    testAnnotationProcessor ("org.projectlombok:lombok")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperties(System.getProperties().toMap() as Map<String,Object>)
}

tasks.getByName<BootRun>("bootRun") {
    systemProperties(System.getProperties().toMap() as Map<String,Object>)
}

tasks.withType<Test> {
    dependsOn("spotlessApply")
}

springBoot {
    buildInfo {
        properties {
            group = "com.josdem.jmailer"
            artifact = "jmailer-spring-boot"
        }
    }
}
