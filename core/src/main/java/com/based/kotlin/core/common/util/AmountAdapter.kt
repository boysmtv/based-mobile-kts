package com.based.kotlin.core.common.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal

class AmountAdapter {

    @FromJson
    fun fromJson(value: String) = value.toBigDecimal()

    @ToJson
    fun toJson(value: BigDecimal): Double = value.toDouble()
}