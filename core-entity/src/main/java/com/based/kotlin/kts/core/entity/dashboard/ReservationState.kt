/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.kts.core.entity.dashboard

import android.os.Parcelable
import androidx.annotation.Keep
import com.based.kotlin.kts.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.kts.utilities.constants.Constants.ZERO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class ReservationState(
    @Json(name = "reservationId") val reservationId: String = EMPTY_STRING,
    @Json(name = "currentState") val currentState: String = EMPTY_STRING,
    @Json(name = "customCommand") val customCommand: String = EMPTY_STRING,
    @Json(name = "isCustomerLogout") val isCustomerLogout: Boolean = false,
    @Json(name = "channelOtp") val channelOtp: String = EMPTY_STRING,
    @Json(name = "amount") val amount: BigDecimal = BigDecimal(ZERO),
    @Json(name = "transactionId") val transactionId: String = EMPTY_STRING,
    @Json(name = "transactionIdPosition") val transactionIdPosition: Int = ZERO,
    @Json(name = "code") val code: String = EMPTY_STRING,
    @Json(name = "title") val title: String = EMPTY_STRING,
    @Json(name = "message") val message: String = EMPTY_STRING,
    @Json(name = "recaptureReservation") val recaptureReservation: Boolean = false,
    @Json(name = "data") val parseDataState: ParseDataState? = null
) : Parcelable