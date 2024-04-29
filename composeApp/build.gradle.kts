import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.0-RC1"

}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "11"
            }
        }
    }
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1-Beta")
            implementation("org.jetbrains.androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0-alpha01")
            implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")


            implementation("com.google.android.gms:play-services-location:21.1.0")
            implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")

            implementation("io.ktor:ktor-client-core:2.3.10")
            implementation("io.ktor:ktor-client-cio:2.3.10")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.10")
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.10")

            implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.0")
            implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")




        }
    }
}

android {
    namespace = "com.andercarotfg.weatherappkcmp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.andercarotfg.weatherappkcmp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.12"
    }


    dependencies {
        debugImplementation(libs.compose.ui.tooling)
        implementation("com.google.android.gms:play-services-location:21.1.0")
        implementation("com.google.accompanist:accompanist-permissions:0.35.0-alpha")
    }
}
dependencies {
    implementation(libs.androidx.activity.ktx)
}

