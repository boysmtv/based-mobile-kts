package com.based.kotlin.core.ui.dialog.data

import androidx.annotation.Keep

@Keep
data class DialogNonFinOldBdsDetail(
    val customerDetail: DialogCustomerDetail,
    val bankService: Pair<String, String>? = null,
    val userType: String
)