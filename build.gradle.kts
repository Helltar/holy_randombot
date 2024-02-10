import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.22"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    application
}

group = "com.helltar"
version = "0.1.0"

repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.0.7")
}

application {
    mainClass.set("MainKt")
}

tasks.shadowJar {
    archiveClassifier = ""
}