import org.springframework.boot.gradle.tasks.bundling.BootJar

val groupName = "com.akej24"
val projectVersion = "1.0.0-SNAPSHOT"
val javaVersion = JavaVersion.VERSION_17

plugins {
    id("application")
}

application {
    mainClass.set("com.akej24.chatAndChallengeApp.ChatAndChallengeApplication")
}

buildscript {
    val springBootVersion = "3.1.3"

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
    }
}

allprojects {
    group = groupName
    version = projectVersion

    repositories {
        mavenCentral()
    }

    apply(plugin = "java")
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")

    configure<JavaPluginExtension> {
        sourceCompatibility = javaVersion
        targetCompatibility = javaVersion
    }

    tasks.withType<BootJar> {
        enabled = false
    }

    configurations {
        compileOnly {
            extendsFrom(configurations.annotationProcessor.get())
        }
    }
}

