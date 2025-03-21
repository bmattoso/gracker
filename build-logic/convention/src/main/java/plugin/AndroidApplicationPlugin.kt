package plugin

import com.android.build.api.dsl.CommonExtension
import extension.configureAndroid
import extension.configureCompose
import extension.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply(plugin("kotlinAndroid").pluginId)
                apply(plugin("gracker-android-hilt").pluginId)
                apply(plugin("compose-compiler").pluginId)
            }

            configureAndroid()
            extensions.configure(CommonExtension::class) {
                buildFeatures {
                    buildConfig = true
                }
                configureCompose(this)
            }
        }
    }
}
