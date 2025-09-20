plugins {
    alias(libs.plugins.gracker.android.library)
    alias(libs.plugins.gracker.android.hilt)
    alias(libs.plugins.kotlinx.serialization)
}

android {
    namespace = "br.com.gracker.core.network"

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }

    buildFeatures {
        buildConfig = true
    }

    dependencies {
        implementation(libs.okhttp)
        implementation(libs.retrofit)
        implementation(libs.kotlinx.serialization.json)
        implementation(libs.kotlinx.coroutines.android)

        // Testing dependencies
        testImplementation(libs.junit)
        testImplementation(libs.mockk)
        testImplementation(libs.mockk.android)
        testImplementation(libs.truth)
        testImplementation(libs.kotlinx.coroutines.test)
    }
}

secrets {
    propertiesFileName = "secrets.properties"
}
