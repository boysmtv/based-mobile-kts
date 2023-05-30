package com.based.kotlin.core.di

import android.content.Context
import com.bumptech.glide.Glide
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.Excludes
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpLibraryGlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import dagger.hilt.android.EntryPointAccessors
import okhttp3.OkHttpClient
import java.io.InputStream
import javax.inject.Inject

@Excludes(OkHttpLibraryGlideModule::class)
@GlideModule
class GlideAppModule @Inject constructor() : AppGlideModule() {

    private lateinit var okHttpClient: OkHttpClient

    override fun registerComponents(context: Context, glide: Glide, registry: Registry) {
        okHttpClient = EntryPointAccessors.fromApplication<GlideBuilderEntryPoint>(context).okhttp()
        registry.replace(
            GlideUrl::class.java,
            InputStream::class.java,
            OkHttpUrlLoader.Factory(okHttpClient)
        )
    }
}