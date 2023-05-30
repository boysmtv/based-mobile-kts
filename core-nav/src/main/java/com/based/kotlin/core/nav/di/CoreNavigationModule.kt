package com.based.kotlin.core.nav.di

import com.based.kotlin.core.nav.NavControllerBinder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreNavigationModule {

    @Singleton
    @Provides
    fun provideBinder(): NavControllerBinder = NavControllerBinder()

}