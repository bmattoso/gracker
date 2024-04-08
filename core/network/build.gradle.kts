plugins {
    alias(libs.plugins.gracker.android.library)
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

    dependencies {
        implementation(libs.okhttp)
        implementation(libs.retrofit)

        testImplementation(libs.junit)
    }
}
