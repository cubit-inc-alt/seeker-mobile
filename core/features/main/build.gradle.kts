import app.libs

plugins {
  alias(libs.plugins.feature)
  alias(libs.plugins.kotlinx.serilization)
}


kotlin {
  sourceSets.commonMain.dependencies {
    implementation(libs.jetbrain.navigation.compose)
    implementation(projects.core.features.chat)
  }
}
