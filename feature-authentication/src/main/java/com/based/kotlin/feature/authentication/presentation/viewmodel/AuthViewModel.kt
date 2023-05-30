package com.based.kotlin.feature.authentication.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.based.kotlin.api.authentication.domain.post.PostLoginUseCase
import com.based.kotlin.api.authentication.domain.save.SaveGbLoginType
import com.based.kotlin.api.authentication.domain.save.SaveUserTypeUseCase
import com.based.kotlin.api.splash.domain.load.LoadAppConfigUseCase
import com.based.kotlin.core.common.entity.Result
import com.based.kotlin.core.common.navigation.DeeplinkNavigation
import com.based.kotlin.core.entity.auth.AuthRequest
import com.based.kotlin.core.entity.auth.AuthResponse
import com.based.kotlin.core.entity.auth.EmptyResponse
import com.based.kotlin.core.util.ArgumentConstants.Common.ARGS_KEY_SCENARIO
import com.based.kotlin.utilities.constants.AuthenticationDeeplink.NAVIGATE_TO_AUTHENTICATION_OTP
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.utilities.constants.Constants.ONE
import com.based.kotlin.utilities.constants.ConstantsDeeplink.NAVIGATE_TO_TRANSACTION_CONFIRMATION_CUSTOMER
import com.based.kotlin.utilities.constants.ConstantsDeeplink.NAVIGATE_TO_TRANSACTION_OVERVIEW_CUSTOMER
import com.based.kotlin.utilities.constants.ConstantsDeeplink.NAVIGATE_TO_TRANSACTION_RECEIPT
import com.based.kotlin.utilities.constants.DashboardDeeplink.NAVIGATE_TO_DASHBOARD
import com.based.kotlin.utilities.constants.MainMenuDeeplink.NAVIGATE_TO_MAIN_MENU
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val postLoginUseCase: PostLoginUseCase,
    private val saveUserTypeUseCase: SaveUserTypeUseCase,
    internal val loadAppConfigUseCase: LoadAppConfigUseCase,
    internal val saveGbLoginType: SaveGbLoginType,
    private val deeplinkNavigation: DeeplinkNavigation
) : ViewModel() {

    //region Local
    val loginData by lazy { MutableLiveData<AuthResponse>() }
    val loginCustomerData by lazy { MutableLiveData<AuthResponse>() }
    val loadError by lazy { MutableLiveData<Boolean>() }
    val loading by lazy { MutableLiveData<Boolean>() }
    val loadErrorMessage by lazy { MutableLiveData<String>() }

    var dataAuthResponse = AuthResponse()
    var dataCustomerAuthResponse = AuthResponse()
    var errorMessage: String = EMPTY_STRING
    var userId: String = EMPTY_STRING
    var password: String = EMPTY_STRING

    private val minimumPass: Int = ONE
    private val minimumUserId: Int = ONE
    //endregion

    //region Live Data
    private val _login = MutableLiveData<Result<AuthResponse>>()
    val login: LiveData<Result<AuthResponse>> = _login

    private val _loginCustomer = MutableLiveData<Result<AuthResponse>>()
    val loginCustomer: LiveData<Result<AuthResponse>> = _loginCustomer

    private val _postReservationState = MutableLiveData<Result<EmptyResponse>>()
    val postReservationState: LiveData<Result<EmptyResponse>> = _postReservationState
    //endregion

    //region Invoker
    suspend fun getLogin() =
        postLoginUseCase(
            AuthRequest(
                userId = userId,
                password = password
            )
        ).collect {
            _login.postValue(it)
        }

    fun isLoginButton(username: String, password: String): Boolean =
        username.length >= minimumUserId && password.length >= minimumPass

    fun navigateToNumericAuthorizationAuth() =
        deeplinkNavigation.navigate(NAVIGATE_TO_AUTHENTICATION_OTP)

    fun navigateToTransactionOverviewCustomer() {
        deeplinkNavigation.navigate(NAVIGATE_TO_TRANSACTION_OVERVIEW_CUSTOMER)
    }

    fun navigateToTransactionReceipt() {
        deeplinkNavigation.navigate(NAVIGATE_TO_TRANSACTION_RECEIPT)
    }

    fun navigateToTransactionConfirmationCustomer() =
        deeplinkNavigation.navigate(NAVIGATE_TO_TRANSACTION_CONFIRMATION_CUSTOMER)

    fun navigateToMainMenu() =
        deeplinkNavigation.navigate(NAVIGATE_TO_MAIN_MENU)

}
