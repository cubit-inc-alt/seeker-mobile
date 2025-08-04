import com.android.build.api.dsl.ApkSigningConfig
import app.VariantDimension
import app.loadProperties
import app.namespace


plugins {
  alias(libs.plugins.android.app)
  alias(libs.plugins.compose.compiler)
  alias(libs.plugins.googleServices)
}

android {
  namespace = project.namespace

  val versions = loadProperties("app.versions")
  val appConfig = loadProperties("app.config")

  val appName = appConfig.getProperty("appName")

  defaultConfig {
    applicationId = appConfig.getProperty("appId")
    //noinspection OldTargetApi
    targetSdk = libs.versions.android.targetSdk.get().toInt()
    versionCode = versions.getProperty("versionCode", "1").toInt()
    versionName = versions.getProperty("versionName", "1.0.0")
  }

  buildTypes {
    release {
      isMinifyEnabled = true
      proguardFiles(
        getDefaultProguardFile("proguard-android-optimize.txt"),
        "proguard-rules.pro"
      )
    }

    debug {
      isMinifyEnabled = false
      isDebuggable = true
    }
  }

  flavorDimensions += VariantDimension.name

  signingConfigs {
    create("local") {
      configureWithSigningKeyProperties("android/keys/local.key")
    }
  }

  productFlavors {
    create(VariantDimension.local) {
      dimension = VariantDimension.name
      applicationIdSuffix = ".local"
      signingConfig = signingConfigs["local"]
      resValue("string", "app_name", "$appName Local")
    }

    create(VariantDimension.dev) {
      dimension = "variant"
      applicationIdSuffix = ".dev"
      signingConfig = signingConfigs["local"]
      resValue("string", "app_name", "$appName Dev")
    }

    create(VariantDimension.prod) {
      dimension = "variant"
      resValue("string", "app_name", appName)
    }
  }
}

dependencies {
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.appcompat)
  implementation(libs.androidx.activity.compose)
  implementation(projects.core.app)
  implementation(projects.core.common)
  implementation(projects.core.firestore)
  implementation(libs.koin.core)
  implementation(libs.koin.android)
}

fun ApkSigningConfig.configureWithSigningKeyProperties(propFileName: String) {
  val keyProperties = loadProperties(propFileName)

  storeFile = rootProject.file(keyProperties["storeFile"] as String)
  storePassword = keyProperties["storePassword"] as String
  keyAlias = keyProperties["keyAlias"] as String
  keyPassword = keyProperties["keyPassword"] as String
}

