import com.kaolinmc.gradle.common.*

plugins {
    kotlin("jvm") version "1.9.21"

    id("com.kaolinmc.common") version "0.1"
}

tasks.wrapper {
    gradleVersion = "7.2"
}

dependencies {
    implementation("org.ow2.asm:asm-commons:9.6")

    testImplementation(project(":tiny"))
    testImplementation(project(":proguard"))
}

common {
    publishing {
        publication {
            artifactId = "archive-mapper"

            commonPom {
                name.set("Archive Mapper")
                description.set("A mapping parser for de-obfuscation mappings(proguard)")
                url.set("https://github.com/kaolinmc/archive-mapper")
            }
        }
    }
}

allprojects {
    apply(plugin = "com.kaolinmc.common")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "com.kaolinmc"
    version = "1.3.6-SNAPSHOT"

    repositories {
        mavenCentral()
        kaolin()
    }

    configurations.all {
        resolutionStrategy.cacheChangingModulesFor(0, "seconds")
    }

    java {
        toolchain.languageVersion.set(JavaLanguageVersion.of(8))
    }

    kotlin {
        explicitApi()
    }

    dependencies {
        implementation(commonUtil())
        implementation(archives())

        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))
        testImplementation(kotlin("test"))
    }

    common {
        defaultJavaSettings()

        publishing {
            publication {
                publication {
                    withJava()
                    withSources()
                    withDokka()

                    commonPom {
                        packaging = "jar"

                        withKaolinRepo()

                        defaultDevelopers()
                        gnuLicense()
                        kaolinScm("archive-mapper")
                    }
                }
            }
            repositories {
                kaolin(credentials = propertyCredentialProvider)
            }
        }
    }
}