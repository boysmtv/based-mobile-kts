package com.based.kotlin.core.common.util

import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.math.BigDecimal
import java.math.MathContext.DECIMAL128

class BigDecimalAdapter {

    @FromJson
    fun fromJson(value: String) = BigDecimal(value, DECIMAL128)

    @ToJson
    fun toJson(value: BigDecimal): String = value.toPlainString()
}