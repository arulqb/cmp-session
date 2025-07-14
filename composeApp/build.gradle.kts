import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("app.cash.sqldelight") version "2.1.0"
    kotlin("plugin.serialization") version "2.0.0"
    id("com.google.gms.google-services")
    alias(libs.plugins.crashlytics) apply false
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
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

    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    sourceSets {
        
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.androidx.camera.core)
            implementation(libs.androidx.camera.camera2)
            implementation(libs.androidx.camera.lifecycle)
            implementation(libs.androidx.camera.view)
//            ktor engine
            implementation(libs.ktor.client.okhttp)
//            DB
            implementation(libs.android.driver)
//            firebase
            // Import the BoM for the Firebase platform
            implementation(platform("com.google.firebase:firebase-bom:33.16.0"))

            // Add the dependency for the Firebase Authentication library
            // When using the BoM, you don't specify versions in Firebase library dependencies
            implementation("com.google.firebase:firebase-auth")

            // Also add the dependencies for the Credential Manager libraries and specify their versions
            implementation("androidx.credentials:credentials:1.5.0")
            implementation("androidx.credentials:credentials-play-services-auth:1.5.0")
            implementation("com.google.android.libraries.identity.googleid:googleid:1.1.1")
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
//            resources
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            implementation(libs.kotlinx.datetime)

//            Moko
//            api(libs.moko.permissions)
//            api(libs.moko.permissions.compose)
//            Networking
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.logging)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.encoding)
            implementation(libs.ktor.client.auth)
//            DB
            implementation(libs.sqdelight.coroutine)
//            koin
            implementation(libs.insert.koin.koin.compose)
            implementation(libs.insert.koin.koin.compose.viewmodel)
            implementation(libs.koin.compose.viewmodel.navigation)
//            3rd party
            implementation(libs.cmptoast)
            // logging
            implementation(libs.napier)
//            coil for image
            implementation(libs.coil.compose)
            implementation("io.coil-kt.coil3:coil-network-okhttp:3.2.0")
//            implementation(libs.coil.network.ktor)

//
            implementation(libs.adaptive)
//          adaptive layout for tablet
            implementation(libs.androidx.material3.adaptive.navigation.suite)
        }

        jsMain.dependencies {
            implementation(libs.sqljs.driver)
            implementation(npm("sql.js", "1.6.2"))

            // NPM development dependencies
            // 'implementation' might not be the most semantically correct configuration
            // for a webpack plugin. Often these are configured directly in webpack tasks
            // or use a configuration like 'jsDevelopmentImplementation' if available/customized.
            // However, for just declaring it as a dev dependency that Kotlin/JS should be aware of:
            implementation(devNpm("copy-webpack-plugin", "9.1.0"))
        }

        iosMain.dependencies {
//            ktor engine
            implementation(libs.ktor.client.darwin)
            implementation(libs.native.driver)
        }

        commonTest.dependencies {
            implementation(libs.kotlin.test)

        }
    }
}


android {
    namespace = "com.codingwitharul.bookmyslot"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "com.codingwitharul.bookmyslot"
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
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug") // Explicitly apply your custom debug config
        }
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    signingConfigs {
        getByName("debug") {
            keyAlias = "bookmyslot"
            keyPassword = "123456"
            storeFile = rootProject.file("android.jks")
            storePassword = "123456"
        }
    }
}

sqldelight {
    databases {
        create("BookMySlot") {
            packageName.set("com.codingwitharul.bookmyslot.db")
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}

