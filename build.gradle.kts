plugins {
    alias(libs.plugins.detekt)
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.firebase.crashlytics) apply false
    alias(libs.plugins.firebase.performance) apply false
    alias(libs.plugins.google.secrets) apply false
    alias(libs.plugins.google.services) apply false
    alias(libs.plugins.gracker.android.application) apply false
    alias(libs.plugins.gracker.android.library) apply false
    alias(libs.plugins.gracker.android.hilt) apply false
    alias(libs.plugins.hilt) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.kover) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.paparazzi) apply false
}

val reportMerge by tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    output.set(rootProject.layout.buildDirectory.file("reports/detekt/merge.sarif"))
}

subprojects {
    apply {
        plugin(rootProject.libs.plugins.detekt.get().pluginId)
        plugin(rootProject.libs.plugins.ktlint.get().pluginId)
    }

    detekt {
        basePath = projectDir.path

        config.setFrom(rootProject.file("config/detekt/detekt.yml"))
        buildUponDefaultConfig = true

        tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().configureEach {
            reports {
                html.required.set(true)
                sarif.required.set(true)
            }
            finalizedBy(reportMerge)
        }

        reportMerge {
            input.from(tasks.withType<io.gitlab.arturbosch.detekt.Detekt>().map { it.sarifReportFile })
        }
    }
}

true // Needed to make the Suppress annotation work for the plugins block
