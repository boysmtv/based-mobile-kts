package com.based.kotlin.core.ui.dialog.data.cashwithdrawal

import androidx.annotation.Keep

@Keep
data class DialogCashWithdrawalSourceAccount(
    val label: String,
    val productName: String,
    val accountNumber: String
)