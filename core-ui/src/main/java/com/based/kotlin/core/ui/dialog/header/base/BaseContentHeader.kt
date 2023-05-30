package com.based.kotlin.core.ui.dialog.header.base

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.based.kotlin.core.ui.contract.ViewContract
import com.based.kotlin.core.ui.dialog.header.model.BaseHeader

abstract class BaseContentHeader<binding : ViewBinding, header : BaseHeader> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
) : ConstraintLayout(context, attrs), ViewContract<binding, header> {
    override val viewBinding: binding by lazy { bindLayout() }
}