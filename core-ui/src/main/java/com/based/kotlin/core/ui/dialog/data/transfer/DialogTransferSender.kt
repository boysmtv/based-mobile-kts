package com.based.kotlin.core.ui.dialog.data.transfer

import androidx.annotation.Keep

@Keep
data class DialogTransferSender(
    val label: String,
    val nameTitle: String,
    val name: String,
    val idCardNumber: String,
    val sourceTitle: String,
    val source: String,
    val accountNumber: String
)