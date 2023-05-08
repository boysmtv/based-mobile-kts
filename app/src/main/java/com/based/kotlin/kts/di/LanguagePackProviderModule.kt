package com.based.kotlin.kts.di

import com.based.kotlin.kts.api.splash.data.repository.SplashLanguagePackRepository
import com.based.kotlin.kts.core.data.LanguagePackProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LanguagePackProviderModule {

    @Binds
    @Singleton
    abstract fun bindsLanguagePackProvider(languagePackRepository: SplashLanguagePackRepository): LanguagePackProvider
}