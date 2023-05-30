package com.based.kotlin.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.based.kotlin.core.R
import com.based.kotlin.core.common.presentation.CommonListener
import com.based.kotlin.core.common.presentation.events.EventListener
import com.based.kotlin.core.common.util.JsonUtil
import com.based.kotlin.core.data.CoroutineSafeJob
import com.based.kotlin.core.data.Translator
import com.based.kotlin.core.ui.dialog.base.BaseDataDialog
import com.based.kotlin.core.ui.dialog.common.DialogGeneralError
import com.based.kotlin.core.ui.dialog.common.DialogNoInternet
import com.based.kotlin.core.ui.dialog.common.DialogWithAction
import com.based.kotlin.core.ui.dialog.data.BaseDataDialogGeneral
import com.based.kotlin.core.ui.util.showDialog
import com.based.kotlin.core.ui.widget.dialog.ProgressBarDialog
import com.based.kotlin.core.util.NetworkConnectionLiveData
import com.based.kotlin.core.util.debounce
import com.based.kotlin.utilities.constants.Constants
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.utilities.constants.Constants.MAX_RETRY
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_400
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_401
import com.based.kotlin.utilities.constants.RespondConstants.HttpCode.CODE_403
import com.based.kotlin.utilities.constants.RespondConstants.StatusCode.CODE_30000
import com.based.kotlin.utilities.constants.RespondConstants.StatusCode.CODE_80007
import com.based.kotlin.utilities.constants.RespondConstants.StatusCode.CODE_80400
import javax.inject.Inject

abstract class BaseFragment<T : ViewBinding> : Fragment(), CommonListener {

    var binding: T by viewBinding()

    lateinit var safeCallJob: CoroutineSafeJob

    @Inject
    lateinit var eventListener: EventListener

    @Inject
    lateinit var jsonUtil: JsonUtil

    var initCustomOnLoadState: (() -> Unit)? = null
    var initCustomOnSuccessState: (() -> Unit)? = null
    var initCustomOnErrorState: (() -> Unit)? = null
    private var onReconnect: (() -> Unit)? = null

    private var dialog: ProgressBarDialog? = null
    internal var dialogGeneralError: DialogGeneralError? = null
    private lateinit var dialogNoInternet: DialogNoInternet
    private var needToShowErrorConnection = false
    private var isConnectionAvailable = true

    protected abstract fun initView()
    var retryCount = Constants.ZERO
    protected abstract fun initBinding(inflater: LayoutInflater, container: ViewGroup?): T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = initBinding(inflater, container)
        if (::eventListener.isInitialized) eventListener.common.setRefreshTokenResultListener(this)
        return binding.root
    }

    private fun initCoroutineSafeJob(
        onErrorAction: (() -> Unit)? = null,
        onTimeoutAction: (() -> Unit)? = null,
        onTimeoutCompletion: (() -> Unit)? = null,
        delayAfterInMillis: Long? = null,
    ) {
        safeCallJob = CoroutineSafeJob(context,
            onErrorAction = { onErrorAction?.let { it() } },
            isSessionIdle = { eventListener.isSessionIdle.get() },
            onTimeoutAction = { onTimeoutAction?.let { activity?.runOnUiThread { it() } } },
            onTimeoutCompletion = { onTimeoutCompletion?.let { activity?.runOnUiThread { it() } } }
        ).apply { continueProcess = { this@BaseFragment.eventListener.common.continueProcess() } }

        delayAfterInMillis?.let { safeCallJob.addDelay(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initCoroutineSafeJob()
        initView()

        val connectionLiveData = NetworkConnectionLiveData(requireContext())
        connectionLiveData.debounce().observe(viewLifecycleOwner) { isConnected ->
            isConnected?.let {
                needToShowErrorConnection = !it
                isConnectionAvailable = it
                if (::dialogNoInternet.isInitialized) dialogNoInternet.dismiss()
                if (it) onReconnect?.let { action -> action() } else showErrorNoInternetConnection()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::dialogNoInternet.isInitialized && isConnectionAvailable) dialogNoInternet.dismiss()
    }

    override fun onDestroyView() {
        dialog = null
        dialogGeneralError = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        dialog = null
        dialogGeneralError = null
        super.onDestroy()
    }

    private fun showErrorNoInternetConnection() {
        dialogNoInternet = DialogNoInternet()
        dialogNoInternet.show(childFragmentManager, dialogNoInternet.tag)
    }

    fun showDialogWithActionButton(
        dataToDialog: BaseDataDialog,
        actionClickPrimary: () -> Unit,
        actionClickSecondary: (() -> Unit)? = null,
        tag: String? = EMPTY_STRING,
    ) {
        val dialog = DialogWithAction(
            onClickButtonPrimary = { actionClickPrimary() },
            onClickButtonSecondary = { actionClickSecondary?.invoke() }
        ).apply { data = dataToDialog }
        if (tag?.isNotEmpty() == true) dialog.show(childFragmentManager, tag)
        else childFragmentManager.showDialog(dialog)
    }

    fun showHideProgress(isLoading: Boolean) =
        if (isLoading) {
            dialog = ProgressBarDialog()
            dialog?.show(childFragmentManager, dialog?.tag)
        } else {
            dialog?.dismiss()
            dialog = null
        }

    fun showGeneralError(
        data: BaseDataDialogGeneral,
        actionClick: () -> Unit,
        actionClickSecondary: () -> Unit,
    ) {
        data.secondaryIsVisible = retryCount >= MAX_RETRY && data.visibleBackToSplash
        dialogGeneralError = DialogGeneralError(
            data,
            actionClick,
            actionClickSecondary,
            onDismissDialogGeneralError()
        )
        dialogGeneralError?.show(childFragmentManager, tag)
    }

    fun showDialogGeneralError(title: String, message: String) {
        showGeneralError(
            BaseDataDialogGeneral(
                title = title,
                message = message,
                icon = R.drawable.ic_connection_problem,
                textPrimaryButton = translate(R.string.button_general_error_try_again),
                visibleBackToSplash = false,
                dismissOnAction = true
            ),
            actionClick = { dismissDialogGeneralError() }
        ) {}
    }

    private fun onDismissDialogGeneralError(): () -> Unit = {
        dialogGeneralError?.dismiss()
        dialogGeneralError = null
    }

    fun dismissDialogGeneralError() {
        retryCount += Constants.ONE
        dialogGeneralError?.dismiss()
        dialogGeneralError = null
    }

    private fun reloadAgain() {
        retryCount += Constants.ONE
        dialogGeneralError?.dismiss()
        dialogGeneralError = null
        safeCallJob.retryTask()
    }

    override fun restartTask() {
        safeCallJob.retryTask()
    }

    override fun mainErrorHandler(
        code: String?,
        title: String?,
        message: String?,
        httpCode: String?,
    ) {
        errorHandler(code, title, message, httpCode)
    }

    override fun showProgressDialog(isShow: Boolean) {
        showHideProgress(isShow)
    }

    fun setTopBarTitle(title: String) {
        (activity as? BaseActivity<*>)?.setTopBarTitle(title)
    }

    private fun showForceLogoutDialog() {
        dialogGeneralError =
            DialogGeneralError(
                BaseDataDialogGeneral(
                    title = translate(R.string.session_timeout_error_title),
                    message = translate(R.string.session_timeout_error_message),
                    icon = R.drawable.ic_dialog_back_to_branch,
                    textPrimaryButton = translate(R.string.button_general_error_try_again),
                    isCancelable = false,
                    dismissOnAction = true
                ),
                onClickPrimaryButton = { backToSplash() }
            )
        dialogGeneralError?.show(childFragmentManager, tag)
    }

    open fun errorHandler(
        code: String?,
        title: String? = null,
        message: String? = null,
        httpCode: String?,
    ) {
        when {
            httpCode == CODE_400 && code == CODE_80400 ->
                showGeneralError(
                    BaseDataDialogGeneral(
                        title = title,
                        message = message,
                        icon = R.drawable.ic_warning_rounded,
                        textPrimaryButton = translate(R.string.button_general_error_try_again),
                        visibleBackToSplash = true,
                    ),
                    actionClick = { dismissDialogGeneralError() },
                    actionClickSecondary = { backToSplash() },
                )

            httpCode == CODE_401 && code == CODE_80007 -> showForceLogoutDialog()
            httpCode == CODE_403 && code == CODE_30000 -> {
                dialogGeneralError =
                    DialogGeneralError(
                        BaseDataDialogGeneral(
                            title = title,
                            message = message,
                            icon = R.drawable.ic_unauthorized_access,
                            textPrimaryButton = translate(R.string.button_general_error_back),
                            isCancelable = false,
                            dismissOnAction = true
                        ),
                        onClickPrimaryButton = { backToSplash() }
                    )
                dialogGeneralError?.show(childFragmentManager, tag)
            }

            else -> {
                dialog?.dismiss()
                showGeneralError(
                    BaseDataDialogGeneral(
                        title = translate(R.string.dialog_general_error_title_under_maintenance),
                        message = translate(R.string.dialog_general_error_desc_under_maintenance),
                        icon = R.drawable.ic_connection_problem,
                        textPrimaryButton = translate(R.string.button_general_error_try_again),
                        visibleBackToSplash = true,
                        dismissOnAction = true
                    ),
                    actionClick = { dismissDialogGeneralError() },
                    actionClickSecondary = { backToSplash() }
                )
            }
        }
    }

    fun showBackButton(isShow: Boolean) {
        (activity as? BaseActivity<*>)?.showBackButton(isShow)
    }

    fun showTopBar(isShow: Boolean) = (activity as? BaseActivity<*>)?.showTopBar(isShow)

    fun setOnBackScenario(scenario: String) = (activity as? BaseActivity<*>)?.onBackNavigation(scenario)

    fun translate(@StringRes stringId: Int): String = Translator.get(getString(stringId))

    fun translate(locKey: String): String = Translator.get(locKey)
}