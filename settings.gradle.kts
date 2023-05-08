apply {
    from("$rootDir/buildConfig/settings-helper.gradle")
}

val includeIfEnabled = extra.get("includeIfEnabled") as groovy.lang.Closure<*>
val includeAlways = extra.get("includeAlways") as groovy.lang.Closure<*>

includeAlways(":app")
includeIfEnabled(":core")
includeIfEnabled(":core-ui")
includeIfEnabled(":core-entity")
includeIfEnabled(":core-nav")
includeIfEnabled(":utilities:constants")
includeIfEnabled(":utilities:currency")
includeIfEnabled(":api-splash")
includeIfEnabled(":feature-authentication")
include(":api-authentication")
