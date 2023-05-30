package com.based.kotlin.core.entity.auth

import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuthIdentity(
    @Json(name = "accessToken") val accessToken: String = EMPTY_STRING,
    @Json(name = "refreshToken") val refreshToken: String = EMPTY_STRING,
    @Json(name = "validity") val validity: String = EMPTY_STRING
)