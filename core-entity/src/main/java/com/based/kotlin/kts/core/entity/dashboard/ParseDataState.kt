/*
 *
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 */

package com.based.kotlin.kts.core.entity.dashboard

import android.os.Parcelable
import androidx.annotation.Keep
import com.based.kotlin.kts.utilities.constants.Constants.EMPTY_STRING
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@JsonClass(generateAdapter = true)
data class ParseDataState(
    @Json(name = "transactionId") val transactionId: String = EMPTY_STRING,
    @Json(name = "code") val code: String = EMPTY_STRING,
    @Json(name = "title") val title: String = EMPTY_STRING,
    @Json(name = "message") val message: String = EMPTY_STRING,
    @Json(name = "sweepTransactionAmount") val sweepTransactionAmount: String = EMPTY_STRING,
    @Json(name = "sweepProductName") val sweepProductName: String = EMPTY_STRING,
    @Json(name = "sweepAccountNumber") val sweepAccountNumber: String = EMPTY_STRING,
    @Json(name = "sweepCurrency") val sweepCurrency: String = EMPTY_STRING,
    @Json(name = "sweepTransactionId") val sweepTransactionId: String = EMPTY_STRING
) : Parcelable
