package com.based.kotlin.core.ui.dialog.data.cashwithdrawal

data class DialogCashWithdrawalAccountDebit(
    val methodTitle: String,
    val transactionAmount: Pair<String, CharSequence>,
    val transactionFee: Pair<String, CharSequence>,
    val totalTransaction: Pair<String, CharSequence>,
    val feeTitle: String? = null
)