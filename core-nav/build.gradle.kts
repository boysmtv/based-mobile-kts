plugins {
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinParcelize)
    kotlin(BuildPlugins.kapt)
}

apply {
    from("$rootDir/buildConfig/common-builder.gradle")
    from("$rootDir/buildConfig/dagger-dependencies.gradle")
}

val customModulePath: groovy.lang.Closure<Any> by ext

dependencies {
    implementation(Jetpack.navigationUi)

    implementation(customModulePath(CoreModules.core))
    implementation(customModulePath(FeatureModules.featureAuth))
    implementation(customModulePath(FeatureModules.featureAuthOtp))
    implementation(customModulePath(FeatureModules.featureSplash))
    implementation(customModulePath(FeatureModules.featureDashboard))
}