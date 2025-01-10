import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
    java
    id("org.springframework.boot") version "3.3.5"
    id("io.spring.dependency-management") version "1.1.6"
    id ("com.diffplug.spotless") version "6.25.0"
    id ("java")
    id ("jacoco")
}

var springdocVersion = "2.4.0"
group = "com.josdem.jmailer"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

spotless {
    java {
        googleJavaFormat()
        removeUnusedImports()
        endWithNewline()
    }
}

dependencies {
    implementation ("org.springframework.boot:spring-boot-starter-aop")
    implementation ("org.springframework.boot:spring-boot-starter-mail")
    implementation ("org.springframework.boot:spring-boot-starter-activemq")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("org.freemarker:freemarker:2.3.31")
    implementation ("org.apache.activemq:activemq-broker")
    implementation ("javax.jms:javax.jms-api:2.0.1")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("io.projectreactor:reactor-test")
    testCompileOnly ("org.projectlombok:lombok")
    testAnnotationProcessor ("org.projectlombok:lombok")
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