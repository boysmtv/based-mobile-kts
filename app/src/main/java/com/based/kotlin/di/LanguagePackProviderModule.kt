package com.based.kotlin.di

import com.based.kotlin.api.splash.data.repository.SplashLanguagePackRepository
import com.based.kotlin.core.data.LanguagePackProvider
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