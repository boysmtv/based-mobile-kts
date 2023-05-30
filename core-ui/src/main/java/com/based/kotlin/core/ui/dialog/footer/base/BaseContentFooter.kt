package com.based.kotlin.core.ui.dialog.footer.base

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewbinding.ViewBinding
import com.based.kotlin.core.ui.contract.ViewContract
import com.based.kotlin.core.ui.dialog.footer.model.DialogContentFooter

abstract class BaseContentFooter<binding : ViewBinding> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ConstraintLayout(context, attrs), ViewContract<binding, DialogContentFooter> {
    override val viewBinding: binding by lazy { bindLayout() }
    abstract val buttonPrimaryAction: View
}