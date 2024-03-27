package extension

import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

private const val COMPILE_SDK = "compileSdk"
private const val MIN_SDK = "minSdk"
private const val TARGET_SDK = "targetSdk"

fun Project.configureAndroid() {
    extensions.configure<BaseExtension> {
        compileSdkVersion(versionOf(COMPILE_SDK).toInt())

        defaultConfig {
            minSdk = versionOf(MIN_SDK).toInt()
            targetSdk = versionOf(TARGET_SDK).toInt()
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
        }
    }
}
