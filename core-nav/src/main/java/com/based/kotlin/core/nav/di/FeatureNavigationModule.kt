package com.based.kotlin.core.nav.di

import com.based.kotlin.core.common.navigation.DeeplinkNavigation
import com.based.kotlin.core.nav.navigator.DeeplinkNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class FeatureNavigationModule {

    @Binds
    abstract fun bindDeeplinkNavigation(navigation: DeeplinkNavigator): DeeplinkNavigation

}