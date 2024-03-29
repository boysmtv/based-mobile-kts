import org.gradle.kotlin.dsl.DependencyHandlerScope
import org.gradle.kotlin.dsl.project
import org.gradle.kotlin.dsl.support.delegates.ProjectDelegate

const val kotlinVersion = "1.7.10"

object ProductFlavors {
    const val DEV = "dev"
    const val SIT = "sit"
    const val UAT = "uat"
    const val PROD = "production"
    const val PRE_PROD = "preprod"
    const val BETA = "beta"
    const val FLAVOR_DIMENSION = "bds"
    const val APP_NAME_SUFFIX_DEV = " DEV"
    const val APP_NAME_SUFFIX_SIT = " SIT"
    const val APP_NAME_SUFFIX_UAT = " UAT"
    const val APP_NAME_SUFFIX_PRE_PROD = " PREPROD"
    const val APP_NAME_SUFFIX_BETA = " BETA"
    const val APP_NAME_KEY = "app_name"
}

object BuildTypes {
    const val DEBUG = "debug"
    const val RELEASE = "release"
}

object Core {

    private object Versions {
        const val appcompat = "1.1.0"
        const val coreKtx = "1.0.2"
        const val biometric = "1.2.0-alpha03"
        const val appDynamicsRuntime = "21.5.0"
        const val playServicesBase = "17.5.0"
        const val firebaseCrashlytics = "17.3.0"
        const val firebaseDynamicLinksPlugin = "19.1.1"
        const val legacySupportVersion = "1.0.0"
        const val fragmentKtx = "1.3.3"
    }

    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val biometric = "androidx.biometric:biometric:${Versions.biometric}"
    const val appDynamicsRuntime =
        "com.appdynamics:appdynamics-runtime:${Versions.appDynamicsRuntime}"
    const val playServicesBase =
        "com.google.android.gms:play-services-base:${Versions.playServicesBase}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.legacySupportVersion}"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentKtx}"
}

object Presentation {

    private object Versions {
        const val material = "1.6.1"
        const val constraintLayout = "2.1.4"
        const val recyclerView = "1.2.1"
        const val viewPager2 = "1.0.0"
        const val viewPump = "2.0.3"
        const val swipeRefresh = "1.1.0"
    }

    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.viewPager2}"
    const val viewPump = "io.github.inflationx:viewpump:${Versions.viewPump}"
    const val swipeRefresh =
        "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefresh}"
}

object Jetpack {

    private object Versions {
        const val lifecycle = "2.5.1"
        const val navigation = "2.5.2"
        const val room = "2.2.4"
        const val paging = "2.1.1"
        const val workManager = "2.7.0"
        const val securityCrypto = "1.0.0"
        const val cameraXVersion = "1.1.0-alpha06"
        const val cameraXViewVersion = "1.0.0-alpha26"
        const val appDynamics = "21.6.0"
    }

    const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleJava = "androidx.lifecycle:lifecycle-common-java8:${Versions.lifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val paging = "androidx.paging:paging-runtime:${Versions.paging}"
    const val workManager = "androidx.work:work-runtime-ktx:${Versions.workManager}"
    const val securityCrypto = "androidx.security:security-crypto:${Versions.securityCrypto}"
    const val cameraXCore = "androidx.camera:camera-camera2:${Versions.cameraXVersion}"
    const val cameraXLifecycle = "androidx.camera:camera-lifecycle:${Versions.cameraXVersion}"
    const val cameraXView = "androidx.camera:camera-view:${Versions.cameraXViewVersion}"
    const val appDynamicsPlugin = "com.appdynamics:appdynamics-gradle-plugin:${Versions.appDynamics}"
    const val appDynamicsAdeum = "adeum"
}

object Network {

    private object Versions {
        const val moshi = "1.14.0"
        const val retrofit = "2.9.0"
        const val okhttp = "4.9.0"
        const val glide = "4.14.2"
    }

    const val moshiKtx = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiKtxCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val okhttpSse = "com.squareup.okhttp3:okhttp-sse:${Versions.okhttp}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val glideOkHttpIntegration =
        "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
}

object CoreModules {
    const val core = ":core"
    const val coreEntity = ":core-entity"
    const val coreUi = ":core-ui"
    const val coreNav = ":core-nav"
}

object ExternalLib {

    private object Versions {
        const val dagger = "2.44"
        const val threeTenABP = "1.4.1"
        const val i18nLanguagePack = "1.0.0"
        const val dexter = "6.0.2"
        const val chucker = "3.5.2"
        const val timber = "4.7.1"
        const val coroutines = "1.6.4"
        const val stetho = "1.5.1"
        const val slidinguppanel = "3.4.0"
        const val wheelPicker = "1.1.3"
        const val mpChart = "v3.1.0"
        const val shimmer = "0.5.0"
        const val barteks = "2.8.2"
        const val slyCalendarView = "0.0.9"
        const val lottie = "3.4.0"
        const val blurry = "1.6.3"
        const val flexBox = "2.0.1"
        const val fastjson = "1.2.73"
        const val okio = "1.7.0@jar"
        const val donut = "2.1.0"
        const val fancyShowcase = "1.3.3"
        const val assistedInject = "0.5.2"
        const val mlKit = "16.1.1"
        const val zxing = "3.4.0"
        const val leakCanary = "2.7"
        const val compressor = "3.0.1"
        const val iText = "5.5.10"
    }

    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val hilt = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:${Versions.dagger}"
    const val threeTenABP = "com.jakewharton.threetenabp:threetenabp:${Versions.threeTenABP}"
    const val i18nLanguagePack = "com.i18next:i18next-android:${Versions.i18nLanguagePack}"

    const val chucker = "com.github.ChuckerTeam.Chucker:library:${Versions.chucker}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val stethoOkhttp = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

    const val dexter = "com.karumi:dexter:${Versions.dexter}"
    const val slidinguppanel = "com.sothree.slidinguppanel:library:${Versions.slidinguppanel}"
    const val mpChart = "com.github.PhilJay:MPAndroidChart:${Versions.mpChart}"
    const val shimmer = "com.facebook.shimmer:shimmer:${Versions.shimmer}"
    const val barteks = "com.github.barteksc:android-pdf-viewer:${Versions.barteks}"
    const val wheelPicker = "cn.aigestudio.wheelpicker:WheelPicker:${Versions.wheelPicker}"
    const val slyCalenderView = "com.github.psinetron:slycalendarview:${Versions.slyCalendarView}"
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val blurry = "com.eightbitlab:blurview:${Versions.blurry}"
    const val flexBox = "com.google.android:flexbox:${Versions.flexBox}"

    const val fastjson = "com.alibaba:fastjson:${Versions.fastjson}"
    const val okio = "com.squareup.okio:okio:${Versions.okio}"
    const val fancyShowcase = "me.toptas.fancyshowcase:fancyshowcaseview:${Versions.fancyShowcase}"
    const val donut = "app.futured.donut:library:${Versions.donut}"

    const val mlKit = "com.google.mlkit:barcode-scanning:${Versions.mlKit}"
    const val zxing = "com.google.zxing:core:${Versions.zxing}"

    const val magsik = "com.android.support.test.espresso:espresso-core:3.0.2"
    const val leakCanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakCanary}"

    const val compressor = "id.zelory:compressor:${Versions.compressor}"
    const val iText = "com.itextpdf:itextg:${Versions.iText}"
}

object LibraryModules {
    const val zolozSdk = ":libraries:zoloz-sdk"
    const val hsmSdk = ":libraries:hsm-sdk"
}

object Analytic {
    private object Versions {
        const val firebaseAnalytics = "18.0.0"
        const val googleTagManagerAndFirebaseInstallation = "17.0.0"
        const val adsIdentifier = "1.0.0-alpha04"
        const val firebaseMessaging = "22.0.0"
        const val firebasePerformance = "19.0.10"
        const val playServiceAdsIdentifier = "17.0.0"
    }

    const val googleTagManager =
        "com.google.android.gms:play-services-tagmanager:${Versions.googleTagManagerAndFirebaseInstallation}"
    const val adsIdentifier = "androidx.ads:ads-identifier:${Versions.adsIdentifier}"
    const val firebaseMessaging =
        "com.google.firebase:firebase-messaging:${Versions.firebaseMessaging}"
    const val firebasePerformance =
        "com.google.firebase:firebase-perf:${Versions.firebasePerformance}"
    const val firebaseInstallation =
        "com.google.firebase:firebase-installations:${Versions.googleTagManagerAndFirebaseInstallation}"
    const val playServiceAdsIdentifier =
        "com.google.android.gms:play-services-ads-identifier:${Versions.playServiceAdsIdentifier}"
}

object BuildPlugins {

    object Versions {
        const val hiltVersion = "2.44"
        const val gradleVersion = "7.2.2"
        const val navigationGradle = "2.5.2"
        const val detektVersion = "1.21.0"
        const val jfrogVersion = "4.29.1"
        const val googlePlayService = "4.3.4"
        const val jacocoVersion = "0.8.7"
        const val appDynamics = "21.5.0"
        const val firebaseCrashlyticsPlugin = "2.4.1"
        const val firebasePerformancePlugin = "1.3.4"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val navigationGradlePlugin =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationGradle}"
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "android"
    const val kotlinParcelize = "kotlin-parcelize"
    const val mavenPublish = "maven-publish"
    const val kapt = "kapt"
    const val navSafeArgs = "androidx.navigation.safeargs.kotlin"
    const val detekt = "io.gitlab.arturbosch.detekt:detekt-gradle-plugin:${Versions.detektVersion}"
    const val safeArgsNavigation = "androidx.navigation.safeargs.kotlin"
    const val jfrogExtractor =
        "org.jfrog.buildinfo:build-info-extractor-gradle:${Versions.jfrogVersion}"
    const val detektPlugin = "io.gitlab.arturbosch.detekt"
    const val jfrogPlugin = "com.jfrog.artifactory"
    const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val googlePlayServicePlugin =
        "com.google.gms:google-services:${Versions.googlePlayService}"
    const val jacocoCore = "org.jacoco:org.jacoco.core:${Versions.jacocoVersion}"
    const val appDynamicsPlugin =
        "com.appdynamics:appdynamics-gradle-plugin:${Versions.appDynamics}"
    const val appDynamicsAdeum = "adeum"
    const val hiltPlugin = "com.google.dagger.hilt.android"
}

object NetworkSecurity {
    const val unsecureNetworkEnabled = "unsecureNetworkEnabled"
}

object Testing {

    private object Versions {
        const val coreKtx = "1.2.0"
        const val jUnitKtx = "1.1.1"
        const val jUnit = "4.13"
        const val mockito = "3.3.3"
        const val mockitoKotlin = "2.1.0"
        const val coroutinesTest = "1.3.7"
        const val coreTesting = "2.1.0"
        const val faker = "1.3.1"
        const val truth = "1.0.1"
        const val threeten = "1.3.1"
        const val testRule = "1.4.1-alpha03"
        const val espresso = "3.5.0-alpha03"
        const val navigationTesting = "2.3.5"
        const val espressoContrib = "3.3.0"
        const val espressoAccessibility = "3.3.0"
        const val fragmentTesting = "1.1.0-beta01"
        const val androidxTesting = "1.4.1-alpha03"
        const val androidxTestTruth = "1.1.0"
        const val androidTestTruth = "0.43"
        const val leakCanaryTesting = "2.7"
    }

    const val coreKtx = "androidx.test:core-ktx:${Versions.coreKtx}"
    const val jUnitKtx = "androidx.test.ext:junit-ktx:${Versions.jUnitKtx}"
    const val jUnit = "junit:junit:${Versions.jUnit}"
    const val mockitoCore = "org.mockito:mockito-core:${Versions.mockito}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val coroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutinesTest}"
    const val coreTesting = "androidx.arch.core:core-testing:${Versions.coreTesting}"
    const val faker = "io.github.serpro69:kotlin-faker:${Versions.faker}"
    const val truth = "com.google.truth:truth:${Versions.truth}"
    const val threeten = "org.threeten:threetenbp:${Versions.threeten}"
    const val testRule = "androidx.test:rules:${Versions.testRule}"
    const val espresso = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    const val navigationTesting =
        "androidx.navigation:navigation-testing:${Versions.navigationTesting}"
    const val espressoContrib =
        "androidx.test.espresso:espresso-contrib:${Versions.espressoContrib}"
    const val espressoAccessibility =
        "androidx.test.espresso:espresso-accessibility:${Versions.espressoAccessibility}"
    const val fragmentTesting = "androidx.fragment:fragment-testing:${Versions.fragmentTesting}"
    const val fragmentTestingKtx = "androidx.fragment:fragment-ktx:${Versions.fragmentTesting}"
    const val androidxTestCore = "androidx.test:core:${Versions.androidxTesting}"
    const val androidxTestRule = "androidx.test:rules:${Versions.androidxTesting}"
    const val androidxTestRunner = "androidx.test:runner:${Versions.androidxTesting}"
    const val androidxTestTruth = "androidx.test.ext:truth:${Versions.androidxTestTruth}"
    const val androidTestTruth = "com.google.truth:truth:${Versions.androidTestTruth}"
    const val leakCanaryInstruments =
        "com.squareup.leakcanary:leakcanary-android-instrumentation:${Versions.leakCanaryTesting}"
}


object FeatureModules {
    const val featureAuth = ":feature-authentication"
    const val featureAuthOtp = ":feature-authentication-otp"
    const val featureDashboard = ":feature-dashboard"
    const val featureTransaction = ":feature-transaction"
    const val featureSplash = ":feature-splash"
    const val featureSupervisor = ":feature-supervisor"
    const val featureRating = ":feature-rating"
    const val featureWaiting = ":feature-waiting"
    const val featureCustomerInformation = ":feature-customer-information"
    const val featureRecapture = ":feature-recapture"
    const val featureNumericAuthorization = ":feature-numeric-authorization"
    const val featureDebitCardMaintenance = ":feature-debitcard-maintenance"
    const val featureTransfer = ":feature-transfer"
    const val featureCustomerDataMaintenance = ":feature-customer-data-maintenance"
    const val featureUploadDocument = ":feature-upload-document"
    const val transactionConfirmationTransfer = ":transaction:transfer"
    const val transactionConfirmationAccountMaintenance = ":transaction:account-maintenance"
    const val transactionConfirmationSelfService = ":transaction:self-service"
    const val featureCashWithdrawal = ":feature-cash-withdrawal"
}

object ApiModules {
    const val apiAuth = ":api-authentication"
    const val apiDashboard = ":api-dashboard"
    const val apiTransaction = ":api-transaction"
    const val apiDebitCardMaintenance = ":api-debitcard-maintenance"
    const val apiSplash = ":api-splash"
    const val apiCustomerDataMaintenance = ":api-customer-data-maintenance"
    const val apiUploadDocument = ":api-upload-document"
    const val apiAccountMaintenance = ":api-account-maintenance"
    const val apiReference = ":api-reference"
    const val apiTermsAndConditions = ":api-terms-and-conditions"
    const val apiConfig = ":api-config"
    const val apiPartyAuthentication = ":api-party-authentication"
    const val apiLetter = ":api-letter"
    const val apiTransfer = ":api-transfer"
    const val apiSavingAccount = ":api-saving-account"
    const val apiCustomerProfile = ":api-customer-profile"
}

object UtilitiesModules {
    const val utilitiesCurrency = ":utilities:currency"
    const val utilitiesConstants = ":utilities:constants"
}

fun DependencyHandlerScope.featureDependencies() {
    "implementation"(project(FeatureModules.featureAuth))
    "implementation"(project(FeatureModules.featureAuthOtp))
    "implementation"(project(FeatureModules.featureDashboard))
    "implementation"(project(FeatureModules.featureTransaction))
    "implementation"(project(FeatureModules.featureRating))
    "implementation"(project(FeatureModules.featureWaiting))
    "implementation"(project(FeatureModules.featureCustomerInformation))
    "implementation"(project(FeatureModules.featureRecapture))
    "implementation"(project(FeatureModules.featureNumericAuthorization))
    "implementation"(project(FeatureModules.featureDebitCardMaintenance))
    "implementation"(project(FeatureModules.featureCustomerDataMaintenance))
    "implementation"(project(FeatureModules.featureUploadDocument))
    "implementation"(project(FeatureModules.featureTransfer))
    "implementation"(project(FeatureModules.transactionConfirmationTransfer))
    "implementation"(project(FeatureModules.transactionConfirmationAccountMaintenance))
    "implementation"(project(FeatureModules.featureCashWithdrawal))
}

fun DependencyHandlerScope.apiDependencies() {
    "implementation"(project(ApiModules.apiAuth))
    "implementation"(project(ApiModules.apiDashboard))
    "implementation"(project(ApiModules.apiTransaction))
    "implementation"(project(ApiModules.apiDebitCardMaintenance))
    "implementation"(project(ApiModules.apiCustomerDataMaintenance))
    "implementation"(project(ApiModules.apiUploadDocument))
    "implementation"(project(ApiModules.apiAccountMaintenance))
    "implementation"(project(ApiModules.apiReference))
    "implementation"(project(ApiModules.apiTermsAndConditions))
    "implementation"(project(ApiModules.apiConfig))
    "implementation"(project(ApiModules.apiPartyAuthentication ))
    "implementation"(project(ApiModules.apiLetter))
    "implementation"(project(ApiModules.apiTransfer))
    "implementation"(project(ApiModules.apiSavingAccount))
    "implementation"(project(ApiModules.apiCustomerProfile ))
}

fun DependencyHandlerScope.utilitiesDependencies() {
    "implementation"(project(UtilitiesModules.utilitiesCurrency))
}

fun ProjectDelegate.getProperty() {
    return java.util.Properties()
        .load(
            java.io.FileInputStream(
                rootProject.file("$rootDir/buildEnv/artifactory.properties")
            )
        )
}