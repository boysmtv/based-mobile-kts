package com.based.kotlin.core.base

import com.based.kotlin.core.R
import com.based.kotlin.core.common.entity.Result
import com.based.kotlin.core.ui.dialog.common.DialogGeneralError
import com.based.kotlin.core.ui.dialog.data.BaseDataDialogGeneral
import com.based.kotlin.utilities.constants.Constants
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_500
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_504

fun BaseFragment<*>.onErrorIdleTime() {
    dialogGeneralError =
        DialogGeneralError(
            BaseDataDialogGeneral(
                title = translate(R.string.session_timeout_error_title),
                message = translate(R.string.session_timeout_error_message),
                icon = R.drawable.ic_dialog_back_to_branch,
                textPrimaryButton = translate(R.string.session_timeout_error_button),
                isCancelable = false,
                dismissOnAction = true
            ),
            onClickPrimaryButton = { backToSplash() }
        )
    dialogGeneralError?.show(childFragmentManager, tag)
}

fun BaseFragment<*>.onGeneralError(httpCode: String?) {
    showHideProgress(false)
    showGeneralError(
        BaseDataDialogGeneral(
            title = translate(R.string.dialog_general_error_title_under_maintenance),
            message = translate(R.string.dialog_general_error_desc_under_maintenance),
            icon = if (httpCode in setOf(
                    CODE_500,
                    CODE_504
                )
            ) R.drawable.ic_connection_problem
            else R.drawable.ic_no_service,
            textPrimaryButton = "Error",
            secondaryIsVisible = retryCount >= Constants.MAX_RETRY,
            visibleBackToSplash = true,
            dismissOnAction = true
        ),
        actionClick = { dismissDialogGeneralError() },
        actionClickSecondary = { backToSplash() },
    )
}

fun BaseFragment<*>.softInputMode(softInputMode: Int) =
    (activity as? BaseActivity<*>)?.window?.setSoftInputMode(softInputMode)

fun BaseFragment<*>.backToSplash() {
    dialogGeneralError?.dismiss()
    dialogGeneralError = null
    (activity as? BaseActivity<*>)?.backToSplash()
}


fun BaseFragment<*>.showCommonGeneralError(response: Result<Any>) {
    showProgressDialog(false)
    showDialogWithActionButton(
        com.based.kotlin.core.ui.dialog.base.BaseDataDialog(
            title = response.title.orEmpty(),
            content = response.message.orEmpty(),
            secondaryButtonShow = false,
            primaryButtonShow = true,
            primaryButtonText = "Kembali",
            secondaryButtonText = Constants.EMPTY_STRING,
            icon = R.drawable.ic_business_error
        ), {}, {}, Constants.Dialog.TAG_DIALOG
    )
}