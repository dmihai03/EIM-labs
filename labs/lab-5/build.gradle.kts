// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.jetbrainsKotlinAndroid) apply false
}

// Exclude old kotlin-stdlib-jdk8 to avoid duplicate class conflicts
// kotlin-stdlib-jdk8 was merged into kotlin-stdlib in Kotlin 1.8+
subprojects {
    configurations.all {
        exclude(group = "org.jetbrains.kotlin", module = "kotlin-stdlib-jdk8")
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.layout.buildDirectory)
}

