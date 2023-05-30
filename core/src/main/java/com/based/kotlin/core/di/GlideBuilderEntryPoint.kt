package com.based.kotlin.core.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient

@InstallIn(SingletonComponent::class)
@EntryPoint
interface GlideBuilderEntryPoint {
    fun okhttp(): OkHttpClient
}