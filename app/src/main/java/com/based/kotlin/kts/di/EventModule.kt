package com.based.kotlin.kts.di

import com.based.kotlin.kts.core.common.presentation.events.EventListener
import com.based.kotlin.kts.util.presentation.KtsEvent
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class EventModule {
    @Provides
    @Singleton
    fun provideEventListener(event: KtsEvent): EventListener = event
}