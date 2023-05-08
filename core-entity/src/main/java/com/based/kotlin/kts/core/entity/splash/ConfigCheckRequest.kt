/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.kts.core.entity.splash

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class ConfigCheckRequest(
    @Json(name = "channelId") var channelId: String?
)