import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension

plugins {
    alias(libs.plugins.gracker.android.application)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.firebase.performance)
    alias(libs.plugins.google.secrets)
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
            signingConfig = signingConfigs.getByName("debug")

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = true
            }
        }

        debug {
            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = false
            }
        }
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    /**
     * This code snippet is disabling the bundle language split due an error from Lokalise.
     * https://developers.lokalise.com/docs/android-sdk-v2?#limitations-and-special-notes
     */
    @Suppress("UnstableApiUsage")
    bundle {
        language {
            enableSplit = false
        }
    }
}

dependencies {

    kover(project(":history"))
    kover(project(":snapshot"))
    kover(project(":core:network"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.startup)

    implementation(libs.lifecycle.runtime.ktx)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.crashlytics)
    implementation(libs.firebase.performance)

    implementation(libs.lokalise)

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

secrets {
    propertiesFileName = "secrets.properties"
}
