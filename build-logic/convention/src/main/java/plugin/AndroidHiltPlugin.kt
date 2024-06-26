package plugin

import com.android.build.api.dsl.CommonExtension
import extension.dependency
import extension.implementation
import extension.ksp
import extension.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidHiltPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply(plugin("hilt").pluginId)
                apply(plugin("ksp").pluginId)
            }

            extensions.configure(CommonExtension::class) {
                dependencies {
                    implementation(dependency("hilt-android"))
                    ksp(dependency("hilt-compiler"))
                }
            }
        }
    }
}
