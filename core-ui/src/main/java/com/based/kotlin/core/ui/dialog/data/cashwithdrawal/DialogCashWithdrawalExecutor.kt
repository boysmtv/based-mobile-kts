package com.based.kotlin.core.ui.dialog.data.cashwithdrawal

import androidx.annotation.Keep

@Keep
data class DialogCashWithdrawalExecutor(
    val label: String,
    val title: String,
    val name: String,
    val accountNumber: String
)