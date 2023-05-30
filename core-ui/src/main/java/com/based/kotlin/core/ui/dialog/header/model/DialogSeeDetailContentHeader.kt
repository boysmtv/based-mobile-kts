package com.based.kotlin.core.ui.dialog.header.model

import androidx.annotation.Keep
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING

@Keep
data class DialogSeeDetailContentHeader(
    val date: Pair<String, String>,
    val status: Status,
    val title: String,
    val detail: CharSequence?,
    val receiptNumber: Pair<String, String>? = Pair(EMPTY_STRING, EMPTY_STRING),
    val statusTransaction: String = EMPTY_STRING,
    val statusTransactionValue: Status? = null,
    val oldBdsLabel: String = EMPTY_STRING,
    val isOverview: Boolean = false
)