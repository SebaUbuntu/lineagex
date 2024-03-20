/*
 * SPDX-FileCopyrightText: 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.metalavaGradle)
    `maven-publish`
}

android {
    namespace = "lineagex.ui"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(project(":core"))
}

metalava {
    inputKotlinNulls.set(true)
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "lineagex"
            artifactId = "ui"
            version = project.version.toString()

            afterEvaluate {
                from(components["release"])
            }

            pom {
                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                    }
                }

                developers {
                    developer {
                        name.set("The LineageOS Project")
                        url.set("https://lineageos.org")
                    }
                }
            }
        }
    }

    repositories {
        maven {
            name = "local"
            url = uri("${project.rootDir}/.m2")
        }
    }
}
