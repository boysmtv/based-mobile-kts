package com.based.kotlin.core.ui.dialog.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.based.kotlin.core.ui.configuration.BaseDialogSizeConfiguration
import com.based.kotlin.core.ui.databinding.BaseDialogCustomBinding
import com.based.kotlin.core.ui.dialog.content.BaseDialogContent
import com.based.kotlin.core.ui.dialog.footer.base.BaseContentFooter
import com.based.kotlin.core.ui.dialog.header.base.BaseContentHeader
import com.based.kotlin.core.ui.dialog.header.model.BaseHeader

class BaseDialogCustom<Header : BaseHeader, Data>(
    private val dialogSeeDetailContentHeader: BaseContentHeader<*, Header>,
    private val dialogSeeDetailContentFooter: BaseContentFooter<*>,
    private val content: BaseDialogContent<*, Data>,
    private val baseDialogSizeConfiguration: BaseDialogSizeConfiguration = BaseDialogSizeConfiguration()
) : DialogFragment() {

    private lateinit var viewBinding: BaseDialogCustomBinding

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
            setBackgroundDrawableResource(android.R.color.transparent)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        viewBinding = BaseDialogCustomBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewBinding) {
            val cvContainerDetailTransactionLayoutParams = cvContainerCustom.layoutParams
            cvContainerDetailTransactionLayoutParams.apply {
                width = resources.getDimension(baseDialogSizeConfiguration.width).toInt()
                height = resources.getDimension(baseDialogSizeConfiguration.height).toInt()
            }
            val headerLayoutParam = headerDialogCustom.layoutParams
            headerLayoutParam.apply {
                width = resources.getDimension(baseDialogSizeConfiguration.headerWidth).toInt()
                height = resources.getDimension(baseDialogSizeConfiguration.headerHeight).toInt()
            }
            headerDialogCustom.layoutParams = headerLayoutParam
            cvContainerCustom.layoutParams = cvContainerDetailTransactionLayoutParams
            headerDialogCustom.addView(dialogSeeDetailContentHeader)
            footerDialogCustom.addView(dialogSeeDetailContentFooter)
            content.layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            containerDialogArea.addView(content)
        }
    }
}