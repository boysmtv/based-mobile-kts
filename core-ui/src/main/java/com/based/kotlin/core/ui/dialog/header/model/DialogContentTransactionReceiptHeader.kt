package com.based.kotlin.core.ui.dialog.header.model

import androidx.annotation.Keep
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING

@Keep
data class DialogContentTransactionReceiptHeader(
    override val date: Pair<String, String>,
    override val status: Status,
    override val title: String,
    override val detail: CharSequence,
    override val oldBdsLabel: String = EMPTY_STRING,
    val receiptNumber: Pair<String, String>,
    val statusTransaction: Pair<String, ReceiptStatus>,
    override val isCompanyTransaction: Boolean = false,
    override val userType: String? = null
) : BaseHeader(date, status, title, detail, oldBdsLabel, isCompanyTransaction)