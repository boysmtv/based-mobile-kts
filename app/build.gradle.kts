plugins {
    id(BuildPlugins.androidApplication)
    id(BuildPlugins.appDynamicsAdeum)
    kotlin(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinParcelize)
    kotlin(BuildPlugins.kapt)
    id(BuildPlugins.hiltPlugin)
}

apply {
    from("$rootDir/buildConfig/common-builder.gradle")
    from("$rootDir/buildConfig/feature-builder.gradle")
    from("$rootDir/buildConfig/api-builder.gradle")
    from("$rootDir/buildConfig/app-dependencies.gradle")
    from("$rootDir/buildConfig/network-dependencies.gradle")
    from("$rootDir/buildConfig/dagger-dependencies.gradle")
    from("$rootDir/buildConfig/jetpack-dependencies.gradle")
    from("$rootDir/buildConfig/reactive-dependencies.gradle")
    from("$rootDir/gradle/install-git-hooks.gradle")
}

val customModulePath: groovy.lang.Closure<Any> by ext

android {
    buildFeatures {
        viewBinding = true
    }

    packagingOptions {
        resources.pickFirsts.add("META-INF/*")
        resources.pickFirsts.add("META-INF/MANIFEST.MF")
    }

    defaultConfig {
        applicationId = "com.based.kotlin"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        externalNativeBuild {
            cmake {
                cppFlags += listOf("")
                arguments += listOf("-DCMAKE_VERBOSE_MAKEFILE:BOOL=ON")
            }
        }

        buildConfigField(
            "boolean",
            ApiKey.IS_ENABLE_ISOLATED_SERVICE,
            rootProject.extra[ApiKey.IS_ENABLE_ISOLATED_SERVICE].toString()
        )

        testInstrumentationRunnerArguments["listener"] = "leakcanary.FailTestOnLeakRunListener"
    }

    externalNativeBuild {
        cmake {
            path("CMakeLists.txt")
        }
    }
    ndkVersion = "22.0.7026061"

    buildTypes {
        getByName(BuildTypes.DEBUG) {
            isShrinkResources = rootProject.extra["IS_SHRINK_RESOURCES"].toString().toBoolean()
            isMinifyEnabled = rootProject.extra["IS_MINIFY_ENABLED"].toString().toBoolean()
            isDebuggable = rootProject.extra["IS_DEBUGGABLE"].toString().toBoolean()
        }
        getByName(BuildTypes.RELEASE) {
            isShrinkResources = rootProject.extra["IS_SHRINK_RESOURCES"].toString().toBoolean()
            isMinifyEnabled = rootProject.extra["IS_MINIFY_ENABLED"].toString().toBoolean()
            isDebuggable = rootProject.extra["IS_DEBUGGABLE"].toString().toBoolean()
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    productFlavors {
        val unsecureNetworkEnabled =
            rootProject.extra[Secret.ENABLE_UNSECURE_HTTP_PROTOCOL].toString()
        val appName = "Kotlin Learn"
        create(ProductFlavors.DEV) {
            dimension = ProductFlavors.FLAVOR_DIMENSION
            resValue(
                "string",
                ProductFlavors.APP_NAME_KEY,
                appName + ProductFlavors.APP_NAME_SUFFIX_DEV
            )
            manifestPlaceholders[NetworkSecurity.unsecureNetworkEnabled] = unsecureNetworkEnabled
        }
        create(ProductFlavors.SIT) {
            dimension = ProductFlavors.FLAVOR_DIMENSION
            resValue(
                "string",
                ProductFlavors.APP_NAME_KEY,
                appName + ProductFlavors.APP_NAME_SUFFIX_SIT
            )
            manifestPlaceholders[NetworkSecurity.unsecureNetworkEnabled] = unsecureNetworkEnabled
        }
        create(ProductFlavors.UAT) {
            dimension = ProductFlavors.FLAVOR_DIMENSION
            resValue(
                "string",
                ProductFlavors.APP_NAME_KEY,
                appName + ProductFlavors.APP_NAME_SUFFIX_UAT
            )
            manifestPlaceholders[NetworkSecurity.unsecureNetworkEnabled] = unsecureNetworkEnabled
        }
        create(ProductFlavors.PRE_PROD) {
            dimension = ProductFlavors.FLAVOR_DIMENSION
            resValue(
                "string",
                ProductFlavors.APP_NAME_KEY,
                appName + ProductFlavors.APP_NAME_SUFFIX_PRE_PROD
            )
            manifestPlaceholders[NetworkSecurity.unsecureNetworkEnabled] = unsecureNetworkEnabled
        }
        create(ProductFlavors.BETA) {
            dimension = ProductFlavors.FLAVOR_DIMENSION
            resValue(
                "string",
                ProductFlavors.APP_NAME_KEY,
                appName + ProductFlavors.APP_NAME_SUFFIX_BETA
            )
            manifestPlaceholders[NetworkSecurity.unsecureNetworkEnabled] = unsecureNetworkEnabled
        }
        create(ProductFlavors.PROD) {
            dimension = ProductFlavors.FLAVOR_DIMENSION
            resValue(
                "string",
                ProductFlavors.APP_NAME_KEY,
                appName
            )
            manifestPlaceholders[NetworkSecurity.unsecureNetworkEnabled] = unsecureNetworkEnabled
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*jar"))))

    implementation(Core.appDynamicsRuntime)
    implementation(Jetpack.lifecycleLiveData)
    implementation(Jetpack.lifecycleViewModel)
    implementation(Jetpack.navigationFragment)
    implementation(Jetpack.navigationUi)
    implementation(Jetpack.securityCrypto)

    // Network
    implementation(Network.moshiKtx)
    implementation(Network.retrofit)
    implementation(Network.retrofitConverterMoshi)
    implementation(Network.okhttp)
    implementation(Network.okhttpLogging)
    implementation(Network.glide)
    kapt(Network.glideCompiler)
    api(Network.glideOkHttpIntegration) { exclude(group = "glide-parent") }
    kapt(Network.moshiKtxCodegen)

    // Dependency Injection
    implementation(ExternalLib.hiltAndroid)
    kapt(ExternalLib.hiltAndroidCompiler)

    // External
    implementation(ExternalLib.coroutines)
    implementation(ExternalLib.coroutinesAndroid)

    // Utils
    implementation(ExternalLib.threeTenABP)
    implementation(ExternalLib.i18nLanguagePack)
    implementation(Jetpack.lifecycleViewModel)
    implementation(ExternalLib.shimmer)
    implementation(ExternalLib.flexBox)

    // Testing
    implementation(Testing.coroutinesTest)
    androidTestImplementation(Testing.testRule)
    androidTestImplementation(Testing.espresso)
    androidTestImplementation(Testing.navigationTesting)
    androidTestImplementation(Testing.espressoContrib)
    androidTestImplementation(Testing.espressoAccessibility)
    androidTestImplementation(Testing.androidxTestTruth)
    androidTestImplementation(Testing.androidTestTruth)
    debugImplementation(Testing.fragmentTesting)
    debugImplementation(Testing.fragmentTestingKtx)
    debugImplementation(Testing.androidxTestCore)
    debugImplementation(Testing.androidxTestRule)
    debugImplementation(Testing.androidxTestRunner)
    debugImplementation(ExternalLib.leakCanary)
    androidTestImplementation(Testing.leakCanaryInstruments)

    // Logger
    implementation(ExternalLib.timber)
    implementation(ExternalLib.stetho)
    implementation(ExternalLib.stethoOkhttp)
    implementation(ExternalLib.chucker)

    // Presentation
    implementation(Presentation.viewPump)
}

kapt {
    correctErrorTypes = true
}