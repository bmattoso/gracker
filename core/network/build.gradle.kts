plugins {
    alias(libs.plugins.gracker.android.library)
}

android {
    namespace = "br.com.gracker.core.network"
}

dependencies {
    testImplementation(libs.junit)
}
