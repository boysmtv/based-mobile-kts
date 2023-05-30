/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.core.entity.splash

import androidx.annotation.Keep
import com.based.kotlin.core.entity.util.LanguagePack
import com.based.kotlin.core.entity.util.LovResult
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class ConfigCheckResponse(
    @Json(name = "parameterUpdatedNeeded") var parameterUpdatedNeeded: Boolean = false,
    @Json(name = "lovUpdatedNeeded") var lovUpdatedNeeded: Boolean = false,
    @Json(name = "lovData") val lovData: LovResult? = null,
    @Json(name = "parameterData") val parameterData: AppConfigResponse? = null,
    @Json(name = "localization") val localization: LanguagePack? = null
)
