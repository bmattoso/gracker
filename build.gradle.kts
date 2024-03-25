plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kover) apply false
}

subprojects {
    apply {
        plugin(rootProject.libs.plugins.detekt.get().pluginId)
    }

    detekt {
        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
        buildUponDefaultConfig = true
    }
}


true // Needed to make the Suppress annotation work for the plugins block