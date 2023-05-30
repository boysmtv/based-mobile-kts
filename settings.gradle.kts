apply {
    from("$rootDir/buildConfig/settings-helper.gradle")
}

val includeIfEnabled = extra.get("includeIfEnabled") as groovy.lang.Closure<*>
val includeAlways = extra.get("includeAlways") as groovy.lang.Closure<*>

includeAlways(":app")
includeIfEnabled(":core-ui")
includeIfEnabled(":core-nav")
includeIfEnabled(":core-entity")
includeIfEnabled(":core")

includeIfEnabled(":api-splash")
includeIfEnabled(":api-authentication")
includeIfEnabled(":api-dashboard")

includeIfEnabled(":feature-authentication")
includeIfEnabled(":feature-splash")
includeIfEnabled(":feature-dashboard")

includeIfEnabled(":utilities:constants")
includeIfEnabled(":utilities:currency")
include(":feature-authentication-otp")
