pluginManagement {
    repositories {
        maven {
            url = uri("https://maven.kaolinmc.com/releases")
        }
        gradlePluginPortal()
    }
}

rootProject.name = "archive-mapper"
include("transform")
include("tiny")
include("proguard")
include("mcp-legacy")
