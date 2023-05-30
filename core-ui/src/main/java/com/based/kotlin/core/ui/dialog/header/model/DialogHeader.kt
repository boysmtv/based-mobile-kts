package com.based.kotlin.core.ui.dialog.header.model

import androidx.annotation.Keep
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING

@Keep
data class DialogHeader(
    override val date: Pair<String, String>,
    val receiptNumber: Pair<String, String>,
    val statusTransaction: Pair<String, Status>,
    override val status: Status,
    override val title: String,
    override val detail: CharSequence,
    override val isCompanyTransaction: Boolean = false
) : BaseHeader(date, status, title, detail, EMPTY_STRING, isCompanyTransaction)