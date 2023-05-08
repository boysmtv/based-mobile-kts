/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.kts.core.entity.splash

import com.based.kotlin.kts.utilities.constants.Constants.EMPTY_STRING
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AppConfigResponse(
    @Json(name = "parameters") val parameters: List<AppConfig> = listOf(),
    @Json(name = "lastUpdatedTime") val lastUpdatedTime: String = EMPTY_STRING
)