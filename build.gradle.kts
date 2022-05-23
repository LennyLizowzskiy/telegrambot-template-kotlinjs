plugins {
    // BASE PLUGINS - DO NOT REMOVE
    kotlin("js") version "1.6.21"
    kotlin("plugin.serialization") version "1.6.21"
}

group = "ru.lizowzskiy"
version = "1.0-TEMPLATE"

repositories {
    mavenCentral()
}

dependencies {
    // BASE DEPENDENCIES - DO NOT REMOVE
    implementation(npm("node-telegram-bot-api", "0.57.0"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-js:1.6.1")

    // EXTRA

}

kotlin {
    js(IR) {
        binaries.executable()
        nodejs {

        }
    }

    sourceSets.all {
        languageSettings.optIn("kotlin.RequiresOptIn")
    }
}

rootProject.plugins.withType(org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootPlugin::class.java) {
    rootProject.the<org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsRootExtension>().let {
        it.nodeVersion = "16.15.0"
    }
}