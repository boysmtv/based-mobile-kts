package com.based.kotlin.core.entity.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = false)
data class AuthIdentityRequest(
    @Json(name = "refreshToken") val refreshToken: String
)