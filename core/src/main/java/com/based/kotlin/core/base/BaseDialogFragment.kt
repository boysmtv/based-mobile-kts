package com.based.kotlin.core.base

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window.FEATURE_NO_TITLE
import androidx.annotation.DrawableRes
import androidx.fragment.app.DialogFragment
import androidx.viewbinding.ViewBinding
import com.based.kotlin.core.common.presentation.CommonListener
import com.based.kotlin.core.common.presentation.UserInteractionInterceptor
import com.based.kotlin.core.common.presentation.events.EventListener
import com.based.kotlin.core.data.CoroutineSafeJob
import com.based.kotlin.core.ui.dialog.common.DialogGeneralError
import com.based.kotlin.core.ui.dialog.data.BaseDataDialogGeneral
import com.based.kotlin.core.ui.widget.dialog.ProgressBarDialog
import com.based.kotlin.utilities.constants.Constants
import javax.inject.Inject

abstract class BaseDialogFragment<T : ViewBinding> : DialogFragment(), CommonListener {

    @Inject
    lateinit var eventListener: EventListener

    private lateinit var callJob: CoroutineSafeJob
    var dialogInitCustomOnLoadState: (() -> Unit)? = null
    var dialogInitCustomOnSuccessState: (() -> Unit)? = null
    var dialogInitCustomOnErrorState: (() -> Unit)? = null

    protected lateinit var mBinding: T
    private var dialog: ProgressBarDialog? = null

    protected abstract fun initView()
    private var dialogGeneralError: DialogGeneralError? = null
    var retryCount = Constants.ZERO

    protected abstract fun initBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = initBinding(inflater, container)
        eventListener.common.setRefreshTokenResultListener(this)
        return mBinding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val layoutParamsManager = dialog.window?.attributes
        layoutParamsManager?.gravity = Gravity.CENTER
        dialog.window?.requestFeature(FEATURE_NO_TITLE)
        dialog.window?.attributes = layoutParamsManager
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        dialog.setCanceledOnTouchOutside(false)
        return dialog
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCoroutineJob()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        eventListener.common.setNullListener()
        dialog = null
    }

    override fun onStart() {
        super.onStart()
        isCancelable = false
        getDialog()?.window?.let {
            it.callback = UserInteractionInterceptor(it.callback, activity)
        }
    }

    fun showHideProgress(isLoading: Boolean) =
        if (isLoading) {
            dialog = ProgressBarDialog()
            dialog?.show(childFragmentManager, dialog?.tag)
        } else dialog?.dismiss()

    override fun restartTask() {
        callJob.retryTask()
    }

    override fun mainErrorHandler(
        code: String?,
        title: String?,
        message: String?,
        httpCode: String?
    ) {
        errorHandling(code, title, message, httpCode)
    }

    override fun showProgressDialog(isShow: Boolean) {
        showHideProgress(isShow)
    }

    private fun initCoroutineJob(
        onErrorActionState: (() -> Unit)? = null,
        onTimeoutActionState: (() -> Unit)? = null,
        onTimeoutCompletionState: (() -> Unit)? = null,
        delayAfterInMillis: Long? = null
    ) {
        callJob = CoroutineSafeJob(context,
            onErrorAction = { onErrorActionState?.let { it() } },
            isSessionIdle = { eventListener.isSessionIdle.get() },
            onTimeoutAction = { onTimeoutActionState?.let { activity?.runOnUiThread { it() } } },
            onTimeoutCompletion = { onTimeoutCompletionState?.let { activity?.runOnUiThread { it() } } }
        ).apply {
            continueProcess = { this@BaseDialogFragment.eventListener.common.continueProcess() }
        }

        delayAfterInMillis?.let { callJob.addDelay(it) }
    }

    fun showGeneralError(
        visibleBackToSplash: Boolean = true,
        title: String?,
        message: String?,
        @DrawableRes icon: Int,
        actionClick: () -> Unit,
        actionClickSecondary: () -> Unit
    ) {
        val data = BaseDataDialogGeneral(
            title = title,
            message = message,
            icon = icon,
            textPrimaryButton = "Kembali",
            secondaryIsVisible = retryCount >= Constants.MAX_RETRY && visibleBackToSplash,
            dismissOnAction = true
        )
        dialogGeneralError = DialogGeneralError(data, actionClick, actionClickSecondary)
        dialogGeneralError?.show(childFragmentManager, tag)
    }

    fun dismissDialogGeneralError() {
        retryCount += Constants.ONE
        dialogGeneralError?.dismiss()
    }

    private fun backToLogin() {
        dialogGeneralError?.dismiss()
        retryCount = Constants.ZERO
        (activity as? BaseActivity<*>)?.backToLogin()
    }

    fun backToSplash() {
        dialogGeneralError?.dismiss()
        (activity as? BaseActivity<*>)?.backToSplash()
    }

    open fun errorHandling(code: String?, title: String? = null, message: String? = null, httpCode: String?) = Unit

}