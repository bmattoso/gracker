plugins {
    id("gracker.android.app")
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.extension.get()
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
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)

    testImplementation(libs.junit)

    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)

    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
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
