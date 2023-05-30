package com.based.kotlin

import android.app.Application
import com.based.kotlin.api.splash.data.repository.SplashLanguagePackRepository
import com.based.kotlin.core.common.presentation.LayoutInflaterInterceptor
import com.based.kotlin.core.common.presentation.events.EventListener
import com.based.kotlin.core.common.util.AppVersion
import com.based.kotlin.core.data.LanguagePackProvider
import com.based.kotlin.core.data.SupportedLanguage
import com.based.kotlin.core.data.Translator
import com.based.kotlin.utilities.constants.Constants.LANGUAGE_EN_US
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.hilt.android.HiltAndroidApp
import io.github.inflationx.viewpump.ViewPump
import timber.log.Timber
import timber.log.Timber.DebugTree
import javax.inject.Inject

@HiltAndroidApp
class KtsApplication : Application() {

    @Inject
    lateinit var eventListener: EventListener

    @Inject
    lateinit var languagePackProvider: LanguagePackProvider

    override fun onCreate() {
        super.onCreate()
        AppVersion.VERSION_NAME = BuildConfig.VERSION_NAME
        AppVersion.VERSION_CODE = BuildConfig.VERSION_CODE

        AndroidThreeTen.init(this)
        if (BuildConfig.DEBUG) Timber.plant(DebugTree())
        initLibrary()
        ViewPump.init(
            ViewPump.builder()
                .addInterceptor(LayoutInflaterInterceptor())
                .build()
        )
    }

    private fun initLibrary() {
        val supportedLanguage = getSupportedLanguage()
        Translator.init(languagePackProvider, supportedLanguage)
    }

    private fun getSupportedLanguage(): SupportedLanguage {
        return if (languagePackProvider is SplashLanguagePackRepository &&
            (languagePackProvider as SplashLanguagePackRepository).getDefaultLanguage() == LANGUAGE_EN_US
        ) SupportedLanguage.ENGLISH
        else SupportedLanguage.INDONESIAN
    }
}