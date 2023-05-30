package com.based.kotlin.core.ui.dialog.header.model

import android.widget.TextView
import androidx.annotation.Keep

@Keep
data class DialogHeaderView(
    val titleDateTransaction: TextView,
    val valueDateTransaction: TextView,
    val titleStatusTransaction: TextView,
    val valueStatusTransaction: TextView,
    val titleReceiptNumber: TextView,
    val valueReceiptNumber: TextView,
    val statusTransaction: TextView,
    val titleTransaction: TextView,
    val descriptionTransaction: TextView,
    val titleDateOverviewTransaction: TextView,
    val valueDateOverviewTransaction: TextView
)