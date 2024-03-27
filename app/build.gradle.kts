plugins {
    id("gracker.android.app")
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.performance)
    alias(libs.plugins.google.services)
    alias(libs.plugins.kover)
}

android {
    namespace = "br.com.bmattoso.gracker"

    defaultConfig {
        applicationId = "br.com.bmattoso.gracker"
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.performance)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}

koverReport {
    defaults {
        mergeWith("release")
    }

    filters {
        excludes {
            classes(
                "*BuildConfig*",
                "*\$1*",
                "*\$*",
                "*Activity*",
            )
            packages(
                "dagger.hilt.internal",
                "hilt_aggregated_deps",
            )
            annotatedBy(
                "*Composable*",
                "*Entity*",
                "*Dao*",
                "*Database*",
            )
        }
    }
}
