package com.based.kotlin.core.entity.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "code") val code: String? = null,
    @Json(name = "title") val title: String? = null,
    @Json(name = "message") val message: String? = null
)