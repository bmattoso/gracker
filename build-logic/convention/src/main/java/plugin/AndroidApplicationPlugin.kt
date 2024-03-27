package plugin

import com.android.build.api.dsl.CommonExtension
import extension.configureAndroid
import extension.configureCompose
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
                apply("org.jetbrains.kotlin.android")
            }

            configureAndroid()
            extensions.configure(CommonExtension::class) {
                configureCompose(this)
            }
        }
    }
}
