plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.sql.delight)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            api(libs.bundles.ktor.client)
            api(libs.kotlinx.serializer)

            implementation(projects.domain)
            implementation(libs.koin.core)
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.kotlinx.datetime)
            implementation(libs.ksoup)
            implementation(libs.sql.delight)
            implementation(libs.sql.delight.coroutines)
        }
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            implementation(libs.sql.delight.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
            implementation(libs.sql.delight.darwin)
        }
        commonTest.dependencies {
            implementation(libs.koin.test)
        }
    }
}

android {
    namespace = "com.team23.neuracrsrecipes"
    compileSdk = 34
    defaultConfig {
        minSdk = 23
    }
}


sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("data")
        }
    }
}
