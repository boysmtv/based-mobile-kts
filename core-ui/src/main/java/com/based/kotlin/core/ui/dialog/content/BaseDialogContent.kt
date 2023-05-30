package com.based.kotlin.core.ui.dialog.content

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.based.kotlin.core.ui.contract.ViewContract

abstract class BaseDialogContent<binding : ViewBinding, data> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs), ViewContract<binding, data> {
    override val viewBinding: binding by lazy { bindLayout() }
}