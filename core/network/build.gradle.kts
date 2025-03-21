plugins {
    alias(libs.plugins.gracker.android.library)
    alias(libs.plugins.gracker.android.hilt)
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

        testImplementation(libs.junit)
    }
}

secrets {
    propertiesFileName = "secrets.properties"
}
