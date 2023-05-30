package com.based.kotlin.core.ui.dialog.header.model

import androidx.annotation.Keep
import com.based.kotlin.utilities.constants.Constants.ZERO

@Keep
data class Status(
    val value: String,
    val textColor: Int,
    val background: Int,
    val backgroundResource: Int = ZERO
)