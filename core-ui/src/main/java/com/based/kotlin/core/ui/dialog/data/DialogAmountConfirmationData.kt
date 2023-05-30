package com.based.kotlin.core.ui.dialog.data

import androidx.annotation.DrawableRes
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING
import java.math.BigDecimal

data class DialogAmountConfirmationData(
    val title: String = EMPTY_STRING,
    val description: String = EMPTY_STRING,
    val buttonText: String = EMPTY_STRING,
    @DrawableRes val icon: Int? = null,
    val tncText: String = EMPTY_STRING,
    val totalAmountLabel: String = EMPTY_STRING,
    val totalAmount: BigDecimal,
    val totalAmountCounted: String? = null,
    val currency: String,
    val spelledCurrency: String? = null,
    val spelledDecimalCurrency: String? = null
)