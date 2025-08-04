import io.gitlab.arturbosch.detekt.Detekt
import org.jlleitschuh.gradle.ktlint.tasks.KtLintCheckTask
import org.jlleitschuh.gradle.ktlint.tasks.KtLintFormatTask

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.jetbrainsCompose) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.kotlinAndroid) apply false
    alias(libs.plugins.ktlint) apply false
    alias(libs.plugins.detekt) apply false
    alias(libs.plugins.buildkonfig) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
    alias(libs.plugins.ksp) apply false
    alias(libs.plugins.android.room) apply false
    alias(libs.plugins.googleServices) apply false
}


tasks.withType<KtLintFormatTask> {
    dependsOn(gradle.includedBuild("build-logic").task(":ktlintFormat"))
}

tasks.withType<KtLintCheckTask> {
    dependsOn(gradle.includedBuild("build-logic").task(":ktlintCheck"))
}

tasks.withType<Detekt> {
    dependsOn(gradle.includedBuild("build-logic").task(":detekt"))
}
