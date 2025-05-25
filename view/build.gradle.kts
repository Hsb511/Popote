import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_1_8)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "view"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(projects.presentation)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.runtime)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(libs.kamel)
            implementation(libs.koin.compose)
            api(libs.voyager)
            implementation(libs.voyager.transitions)
        }
        androidMain.dependencies {
            implementation(compose.uiTooling)
            implementation(libs.androidx.activity.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.team23.view"
    compileSdk = 35
    defaultConfig {
        minSdk = 23
    }
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")
    sourceSets["main"].res.srcDirs("src/commonMain/resources")
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.team23.view"
    generateResClass = auto
}