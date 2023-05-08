plugins {
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinParcelize)
    kotlin(BuildPlugins.kapt)
}

apply {
    from("$rootDir/buildConfig/common-builder.gradle")
    from("$rootDir/buildConfig/local-aar-config.gradle")
    from("$rootDir/buildConfig/local-aar.gradle")
    from("$rootDir/buildConfig/dagger-dependencies.gradle")
    from("$rootDir/buildConfig/hilt-builder.gradle")
}

val customModulePath: groovy.lang.Closure<Any> by ext

android {

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        buildConfigField(
            "String",
            Secret.SECRET_PREFERENCE,
            rootProject.extra[Secret.SECRET_PREFERENCE].toString()
        )
        buildConfigField(
            "String",
            Secret.PREFERENCE,
            rootProject.extra[Secret.PREFERENCE].toString()
        )
        buildConfigField(
            "boolean",
            Secret.ENABLE_BLUR,
            rootProject.extra[Secret.ENABLE_BLUR].toString()
        )
        buildConfigField(
            "boolean",
            Secret.ENABLE_SS,
            rootProject.extra[Secret.ENABLE_SS].toString()
        )
        buildConfigField(
            "boolean",
            Secret.ENABLE_API_LOG,
            rootProject.extra[Secret.ENABLE_API_LOG].toString()
        )
        buildConfigField(
            "Integer",
            ApiKey.API_CONNECT_TIMEOUT,
            rootProject.extra[ApiKey.API_CONNECT_TIMEOUT].toString()
        )
        buildConfigField(
            "Integer",
            ApiKey.API_WRITE_TIMEOUT,
            rootProject.extra[ApiKey.API_WRITE_TIMEOUT].toString()
        )
        buildConfigField(
            "Integer",
            ApiKey.API_READ_TIMEOUT,
            rootProject.extra[ApiKey.API_READ_TIMEOUT].toString()
        )
        buildConfigField(
            "String",
            Secret.SECRET_PUBLIC_KEY,
            rootProject.extra[Secret.SECRET_PUBLIC_KEY].toString()
        )
        buildConfigField(
            "boolean",
            Secret.ENABLE_TRUSTED_BASE_URL,
            rootProject.extra[Secret.ENABLE_TRUSTED_BASE_URL].toString()
        )
        buildConfigField(
            "boolean",
            Secret.ENABLE_TRUST_MANAGER,
            rootProject.extra[Secret.ENABLE_TRUST_MANAGER].toString()
        )
        buildConfigField(
            "boolean",
            Secret.ENABLE_UNSECURE_HTTP_PROTOCOL,
            rootProject.extra[Secret.ENABLE_UNSECURE_HTTP_PROTOCOL].toString()
        )
        buildConfigField(
            "String",
            ApiKey.BASE_URL,
            rootProject.extra[ApiKey.BASE_URL].toString()
        )
        buildConfigField(
            "String",
            Secret.APPDYNAMICS_APP_KEY,
            rootProject.extra[Secret.APPDYNAMICS_APP_KEY].toString()
        )
        buildConfigField(
            "String",
            Secret.APPDYNAMICS_COLLECTOR_URL,
            rootProject.extra[Secret.APPDYNAMICS_COLLECTOR_URL].toString()
        )
        buildConfigField(
            "boolean",
            Secret.IS_ENABLE_APPD,
            rootProject.extra[Secret.IS_ENABLE_APPD].toString()
        )
    }
    buildTypes {
        getByName("debug")
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*jar"))))

    implementation(Jetpack.lifecycleLiveData)
    implementation(Jetpack.lifecycleViewModel)
    implementation(Jetpack.lifecycleJava)
    implementation(Jetpack.navigationFragment)
    implementation(Jetpack.navigationUi)
    implementation(Jetpack.securityCrypto)
    implementation(Jetpack.paging)
    implementation(Jetpack.workManager)

    // Network
    implementation(Network.moshiKtx)
    implementation(Network.retrofit)
    implementation(Network.retrofitConverterMoshi)
    implementation(Network.okhttp)
    implementation(Network.okhttpLogging)
    implementation(Network.okhttpSse)
    implementation(Network.glide)
    kapt(Network.glideCompiler)
    api(Network.glideOkHttpIntegration) { exclude(group = "glide-parent") }
    kapt(Network.moshiKtxCodegen)

    // External
    implementation(ExternalLib.coroutines)
    implementation(ExternalLib.coroutinesAndroid)

    // Utils
    implementation(ExternalLib.dexter)
    implementation(ExternalLib.threeTenABP)
    implementation(ExternalLib.i18nLanguagePack)

    // Logger
    implementation(ExternalLib.timber)
    implementation(ExternalLib.stetho)
    implementation(ExternalLib.stethoOkhttp)
    implementation(ExternalLib.chucker)

    // Presentation
    implementation(Presentation.viewPump)

    implementation(customModulePath(CoreModules.coreUi))
    implementation(customModulePath(CoreModules.coreEntity))
    implementation(customModulePath(UtilitiesModules.utilitiesCurrency))

    //Document
    implementation(ExternalLib.compressor)
    implementation(ExternalLib.iText)

    //Camera
    implementation(Jetpack.cameraXCore)
    implementation(Jetpack.cameraXView)
    implementation(Jetpack.cameraXLifecycle)

    // Implement project
    implementation(customModulePath(CoreModules.coreUi))
    implementation(customModulePath(CoreModules.coreEntity))
    implementation(customModulePath(UtilitiesModules.utilitiesCurrency))
}
