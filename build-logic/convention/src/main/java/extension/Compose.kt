package extension

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

internal fun Project.configureCompose(
    commonExtension: CommonExtension<*, *, *, *, *, *>,
) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        dependencies {
            val composeBom = dependency("compose-bom")

            implementation(platform(composeBom))
            androidTestImplementation(platform(composeBom))

            implementation(dependency("androidx-activity-compose"))

            implementation(dependency("material3"))

            implementation(dependency("ui"))
            implementation(dependency("ui-graphics"))
            implementation(dependency("ui-tooling-preview"))
            androidTestImplementation(dependency("ui-test-junit4"))

            debugImplementation(dependency("ui-tooling"))
            debugImplementation(dependency("ui-test-manifest"))
        }
    }
}
