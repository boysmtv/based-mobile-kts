package com.based.kotlin.core.entity.auth

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class LogoutResponse(
    @Json(name = "success") var success: String? = null
) : Parcelable