import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
    id("org.springframework.boot") version "3.4.3"
    id("io.spring.dependency-management") version "1.1.7"
    id ("com.diffplug.spotless") version "7.0.2"
    id("org.sonarqube") version "6.0.1.5171"
    id("org.jetbrains.kotlin.jvm") version "2.1.10"
    id ("java")
    id ("jacoco")
}

val springdocVersion = "2.8.6"
val freeMarkerVersion = "2.3.34"
val jmsApiVersion = "2.0.1"
val mockitoKotlinVersion = "5.4.0"

group = "com.josdem.jmailer"

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
        property("sonar.projectKey", "jmailer")
        property("sonar.organization", "josdem-io")
        property("sonar.host.url", "https://sonarcloud.io")
        property("sonar.coverage.jacoco.xmlReportPaths", "services/build/reports/jacoco/test/jacocoTestReport.xml")
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
    implementation ("org.springframework.boot:spring-boot-starter-aop")
    implementation ("org.springframework.boot:spring-boot-starter-mail")
    implementation ("org.springframework.boot:spring-boot-starter-activemq")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.freemarker:freemarker:$freeMarkerVersion")
    implementation ("org.apache.activemq:activemq-broker")
    implementation ("javax.jms:javax.jms-api:$jmsApiVersion")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    testCompileOnly ("org.projectlombok:lombok")
    testAnnotationProcessor ("org.projectlombok:lombok")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.mockito.kotlin:mockito-kotlin:$mockitoKotlinVersion")
}

jacoco {
    toolVersion = "0.8.11"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
    }
}

the<DependencyManagementExtension>().apply {
    imports {
        mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Test> {
    dependsOn("spotlessApply")
}
