package plugin

import com.android.build.api.dsl.CommonExtension
import extension.configureAndroid
import extension.configureCompose
import extension.plugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidLibraryPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.library")
                apply(plugin("kotlinAndroid").pluginId)
                apply(plugin("kover").pluginId)
                apply(plugin("google-secrets").pluginId)
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
