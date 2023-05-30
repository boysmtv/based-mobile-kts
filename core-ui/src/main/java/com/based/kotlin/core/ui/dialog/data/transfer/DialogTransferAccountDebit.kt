package com.based.kotlin.core.ui.dialog.data.transfer

data class DialogTransferAccountDebit(
    val methodTitle: String,
    val transactionAmount: Pair<String, CharSequence>,
    val transactionFee: Pair<String, CharSequence>,
    val totalTransaction: Pair<String, CharSequence>
)