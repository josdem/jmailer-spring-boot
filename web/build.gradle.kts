import org.springframework.boot.gradle.tasks.run.BootRun

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
version = "1.2.4"


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
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.boot:spring-boot-starter-aop")
    implementation ("org.springframework.boot:spring-boot-starter-mail")
    implementation ("org.springframework.boot:spring-boot-starter-actuator")
    implementation ("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation ("org.springframework.boot:spring-boot-starter-activemq")
    implementation ("org.springframework.boot:spring-boot-starter-validation")
    implementation ("javax.jms:javax.jms-api:2.0.1")
    implementation ("org.freemarker:freemarker:2.3.31")
    implementation ("org.springdoc:springdoc-openapi-starter-webmvc-ui:$springdocVersion")
    implementation (project (":services"))
    compileOnly ("org.projectlombok:lombok")
    annotationProcessor ("org.projectlombok:lombok")
    testImplementation ("org.springframework.boot:spring-boot-starter-test")
    testCompileOnly ("org.projectlombok:lombok")
    testAnnotationProcessor ("org.projectlombok:lombok")
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