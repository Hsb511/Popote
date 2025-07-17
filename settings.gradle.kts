enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "PopoteV2"
include(":androidApp")
include(":iosApp")
include(":presentation")
include(":view")
include(":domain")
include(":data")

project(":domain").projectDir = file("domain")
project(":data").projectDir = file("data")
project(":presentation").projectDir = file("presentation")
project(":view").projectDir = file("view")