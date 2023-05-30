package com.based.kotlin.core.base

import android.view.View
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import com.based.kotlin.core.R
import com.based.kotlin.core.ui.extensions.hide
import com.based.kotlin.core.ui.extensions.show
import com.based.kotlin.core.ui.widget.error.ErrorView
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_409
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_503
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_504

fun ErrorView.showErrorView(
    httpCode: String?,
    title: String?,
    message: String?,
    isShowIcon: Boolean
) {

    visibility = View.VISIBLE
    if (isShowIcon) binding.ivError.show()
    else binding.ivError.hide()

    when (httpCode) {
        CODE_504 -> {
//            setMessage(resources.translate(R.string.general_error_message_time_out))
//            setTitle(resources.translate(R.string.general_error_title_time_out))
            setImage(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_getaway_timeout,
                    null
                )
            )
        }

        CODE_503 -> {
//            setMessage(resources.translate(R.string.general_error_message_service_not_found))
//            setTitle(resources.translate(R.string.general_error_title_service_not_found))
            setImage(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_no_service,
                    null
                )
            )
        }

        CODE_409 -> {
            message?.let { setMessage(it) }
            title?.let { setTitle(it) }
            setImage(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_business_error,
                    null
                )
            )
        }

        else -> {
            message?.let { setMessage(it) }
            title?.let { setTitle(it) }
            setImage(
                ResourcesCompat.getDrawable(
                    resources,
                    R.drawable.ic_connection_problem,
                    null
                )
            )
        }
    }
}