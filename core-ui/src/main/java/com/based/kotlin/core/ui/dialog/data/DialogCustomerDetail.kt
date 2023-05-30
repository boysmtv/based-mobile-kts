package com.based.kotlin.core.ui.dialog.data

import androidx.annotation.Keep
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING

@Keep
data class DialogCustomerDetail(
    val detailLabel: String,
    val name: Pair<String, String>,
    val customerCif: String,
    val accountNumberLabel: String = EMPTY_STRING,
    val productName: String? = null,
    val accountNumber: String? = null,
    val phoneNumber: Pair<String, String>,
    val address: Pair<String, String>? = null
)
