package com.based.kotlin.kts.di

import android.content.Context
import com.appdynamics.eumagent.runtime.AgentConfiguration
import com.appdynamics.eumagent.runtime.Instrumentation
import com.appdynamics.eumagent.runtime.InteractionCaptureMode
import com.based.kotlin.kts.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDynamicsModule {

    @Singleton
    @Provides
    internal fun provideAgentConfiguration(@ApplicationContext application: Context): AgentConfiguration =
        AgentConfiguration.builder()
            .withContext(application.applicationContext)
            .withAppKey(BuildConfig.APPDYNAMICS_APP_KEY)
            .withCollectorURL(BuildConfig.APPDYNAMICS_COLLECTOR_URL)
            .withLoggingEnabled(false)
            .withLoggingLevel(Instrumentation.LOGGING_LEVEL_NONE)
            .withScreenshotsEnabled(false)
            .withInteractionCaptureMode(InteractionCaptureMode.All)
            .build()
}