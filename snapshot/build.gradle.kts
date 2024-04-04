plugins {
    id("gracker.android.library")
    alias(libs.plugins.paparazzi)
}

android {
    namespace = "br.com.gracker.snapshot"
}

dependencies {
    implementation(project(":history"))
}

plugins.withId("app.cash.paparazzi") {
    afterEvaluate {
        dependencies.constraints {
            add("testImplementation", "com.google.guava:guava") {
                attributes {
                    attribute(
                        TargetJvmEnvironment.TARGET_JVM_ENVIRONMENT_ATTRIBUTE,
                        objects.named(TargetJvmEnvironment::class.java, TargetJvmEnvironment.STANDARD_JVM),
                    )
                }
                because(
                    "LayoutLib and sdk-common depend on Guava's -jre published variant." +
                        "See https://github.com/cashapp/paparazzi/issues/906.",
                )
            }
        }
    }
}
