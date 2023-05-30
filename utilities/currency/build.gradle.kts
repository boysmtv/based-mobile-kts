plugins {
    id(BuildPlugins.androidLibrary)
    kotlin(BuildPlugins.kotlinAndroid)
}

apply {
    from("$rootDir/buildConfig/common-config.gradle")
}