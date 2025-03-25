import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "br.com.bmattoso.gracker.buildlogic"

kotlin {
    jvmToolchain(17)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}


dependencies {
    compileOnly(libs.android.gradle.plugin)
    compileOnly(libs.kotlin.gradle.plugin)
}

gradlePlugin {
    plugins {
        register("androidLibrary") {
            id = "gracker.android.library"
            implementationClass = "plugin.AndroidLibraryPlugin"
        }
        register("androidApplications") {
            id = "gracker.android.app"
            implementationClass = "plugin.AndroidApplicationPlugin"
        }
        register("androidHilt") {
            id = "gracker.android.hilt"
            implementationClass = "plugin.AndroidHiltPlugin"
        }
    }
}
