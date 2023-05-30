package com.based.kotlin.core.di

import android.app.Application
import android.content.Context
import com.based.kotlin.core.common.util.CorePlainPrefManager
import com.based.kotlin.core.common.util.security.CorePrefManager
import com.based.kotlin.core.common.util.security.SecurePrefManager
import com.based.kotlin.core.data.CoroutineDispatcherProvider
import com.based.kotlin.core.data.DefaultDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class CoreCommonModule {

    @Singleton
    @Provides
    fun provideCorePrefManager(@ApplicationContext app: Context) =
        CorePrefManager(app as Application)

    @Singleton
    @Provides
    fun provideCoroutineDispacther(): CoroutineDispatcherProvider = DefaultDispatcherProvider()

    @Singleton
    @Provides
    fun provideSecurePrefManager(corePrefManager: CorePrefManager) = SecurePrefManager(corePrefManager)

    @Singleton
    @Provides
    fun provideCorePlainPrefManager(@ApplicationContext app: Context) = CorePlainPrefManager(app as Application)

}