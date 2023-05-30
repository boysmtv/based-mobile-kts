package com.based.kotlin.core.entity.auth

import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@Keep
@JsonClass(generateAdapter = true)
data class AuthRequest(
    @Json(name = "userId") var userId: String? = null,
    @Json(name = "password") var password: String? = null
)
