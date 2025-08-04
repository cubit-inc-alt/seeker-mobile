import org.gradle.kotlin.dsl.implementation

plugins {
  alias(libs.plugins.multiplatform.library.compose)
  alias(libs.plugins.kotlinx.serilization)
}

kotlin {
  sourceSets.commonMain.dependencies {
    implementation(projects.core.models)
    implementation(projects.core.common)
  }

  sourceSets.androidMain.dependencies {
    api(project.dependencies.platform(libs.firebase.bom))
    api(libs.firebase.firestore)
  }
}
