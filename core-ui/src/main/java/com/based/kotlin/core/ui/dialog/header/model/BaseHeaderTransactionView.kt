package com.based.kotlin.core.ui.dialog.header.model

import android.widget.TextView
import androidx.annotation.Keep

@Keep
data class BaseHeaderTransactionView(
    val titleDateTransaction: TextView,
    val valueDateTransaction: TextView,
    val statusOldBds: TextView,
    val statusTransaction: TextView,
    val titleTransaction: TextView,
    val descriptionTransaction: TextView
)