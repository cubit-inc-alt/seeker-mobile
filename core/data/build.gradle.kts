plugins {
    alias(libs.plugins.multiplatform.library.compose)
    alias(libs.plugins.kotlinx.serilization)
}


kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.datastore)
            implementation(projects.core.database)
            implementation(projects.core.common)
            implementation(projects.core.network)
            api(projects.core.models)
            implementation(projects.core.resources)
            implementation(projects.core.firestore)
        }
    }
}
