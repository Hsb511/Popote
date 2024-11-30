plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.jetbrainsCompose)
}

val majorVersion = 2
val minorVersion = 0
val fixVersion = 0

android {
    namespace = "com.team23.neuracrsrecipes"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.team23.neuracrsrecipes"
        minSdk = 23
        targetSdk = 35
        versionCode = majorVersion * 10000 + minorVersion * 100 + fixVersion
        versionName = "$majorVersion.$minorVersion.$fixVersion"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.13"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(projects.data)
    implementation(projects.domain)
    implementation(projects.presentation)
    implementation(projects.view)
    implementation(libs.androidx.activity.compose)
    implementation(libs.koin.android)
}
