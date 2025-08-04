import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import app.namespace

plugins {
    alias(libs.plugins.multiplatform.library.compose)
}

val binaryName = "AppCore"

fun KotlinNativeTarget.configureBinary() {
    binaries.framework {
        baseName = binaryName
        isStatic = true

        binaryOption("bundleId", project.namespace)

        export(projects.core.app)
        export(projects.core.ui)
        export(projects.core.firestore)
    }
}

kotlin {
    iosArm64().configureBinary()
    iosSimulatorArm64().configureBinary()

    sourceSets {
        commonMain.dependencies {
            api(projects.core.app)
        }
    }
}
