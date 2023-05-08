package com.based.kotlin.kts.api.splash.data.local

import android.content.Context
import com.based.kotlin.kts.core.R
import com.based.kotlin.kts.core.entity.util.LanguagePack
import com.squareup.moshi.JsonAdapter
import javax.inject.Inject

class SplashLanguagePackResource @Inject constructor(
    private val context: Context,
    private val adapter: JsonAdapter<LanguagePack>
) {

    fun get(): LanguagePack? {
        val inputStream = context.resources.openRawResource(R.raw.language_pack)
        val reader = inputStream.bufferedReader().use { it.readText() }

        return adapter.fromJson(reader)
    }

}