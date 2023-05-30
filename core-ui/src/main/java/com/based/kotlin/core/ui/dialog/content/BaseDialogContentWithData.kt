package com.based.kotlin.core.ui.dialog.content

import android.content.Context
import android.util.AttributeSet
import androidx.viewbinding.ViewBinding

abstract class BaseDialogContentWithData<binding : ViewBinding, data> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : BaseDialogContent<binding, data>(context, attrs) {
    abstract fun getData(): data
}