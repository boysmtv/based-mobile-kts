/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.api.splash.data.local

import android.content.Context
import com.based.kotlin.api.splash.R
import com.based.kotlin.core.entity.splash.AppConfig
import com.based.kotlin.core.entity.splash.AppConfigResponse
import com.squareup.moshi.JsonAdapter
import javax.inject.Inject

class SplashAppConfigResource @Inject constructor(
    private val context: Context,
    private val adapter: JsonAdapter<AppConfigResponse>
) {
    //region GET
    fun get(): List<AppConfig>? {
        val inputStream = context.resources.openRawResource(R.raw.app_config)
        val reader = inputStream.bufferedReader().use { it.readText() }

        return adapter.fromJson(reader)?.parameters
    }
    //endregion
}