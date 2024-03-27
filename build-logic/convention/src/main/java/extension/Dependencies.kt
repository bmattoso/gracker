package extension

import org.gradle.api.Project
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.provider.Provider
import org.gradle.kotlin.dsl.getByType
import org.gradle.plugin.use.PluginDependency

val Project.libs
    get(): VersionCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")

fun Project.dependency(
    dependencyName: String
): Provider<MinimalExternalModuleDependency> = libs.findLibrary(dependencyName).get()

fun Project.versionOf(reference: String): String = libs.findVersion(reference).get().toString()