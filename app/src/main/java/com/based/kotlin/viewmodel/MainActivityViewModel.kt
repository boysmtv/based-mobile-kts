package com.based.kotlin.viewmodel

import android.content.ComponentName
import android.content.ServiceConnection
import android.os.IBinder
import android.os.RemoteException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.based.kotlin.IIsolatedService
import com.based.kotlin.api.authentication.domain.load.LoadLoginDataUseCase
import com.based.kotlin.api.authentication.domain.load.LoadRefreshTokenTimestampUseCase
import com.based.kotlin.api.authentication.domain.load.LoadUserIdUseCase
import com.based.kotlin.api.authentication.domain.post.PostLogoutUseCase
import com.based.kotlin.api.splash.domain.load.LoadAppConfigUseCase
import com.based.kotlin.api.splash.domain.remove.RemoveContextCacheUseCase
import com.based.kotlin.core.common.entity.Result
import com.based.kotlin.core.entity.auth.AuthIdentity
import com.based.kotlin.core.entity.auth.EmptyResponse
import com.based.kotlin.core.entity.auth.LogoutResponse
import com.based.kotlin.core.entity.dashboard.ReservationState
import com.based.kotlin.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val loginDataUseCase: LoadLoginDataUseCase,
    private val postLogoutUseCase: PostLogoutUseCase,
    private val loadUseridUseCase: LoadUserIdUseCase,
    private val loadAppConfigUseCase: LoadAppConfigUseCase,
    internal val removeContextCacheUseCase: RemoveContextCacheUseCase,
    internal val loadRefreshTokenTimestampUseCase: LoadRefreshTokenTimestampUseCase,
) : ViewModel() {

    //region LIVEDATA
    private val _refreshToken = MutableLiveData<Result<AuthIdentity>>()
    val refreshToken: LiveData<Result<AuthIdentity>> = _refreshToken

    private val _logout = MutableLiveData<Result<LogoutResponse>>()
    val logout: LiveData<Result<LogoutResponse>> = _logout

    private val _logoutDualScreen = MutableLiveData<Result<LogoutResponse>>()
    val logoutDualScreen: LiveData<Result<LogoutResponse>> = _logoutDualScreen

    private val _forceLogout = MutableLiveData<Result<EmptyResponse>>()
    val forceLogout: LiveData<Result<EmptyResponse>> = _forceLogout

    private val _reservationState = MutableLiveData<Result<ReservationState>>()
    val reservationState: LiveData<Result<ReservationState>> = _reservationState
    //endregion

    //region Local
    val isAppInForeground = AtomicBoolean(false)
    private val isRefreshTokenCalling = AtomicBoolean(false)
    private val isCanRecallAPI = AtomicBoolean(true)
    var stateParam = mutableMapOf<String, String>()
    val isLogoutDialogShow = AtomicBoolean(false)

    private var serviceBinder: IIsolatedService? = null
    private var bServiceBound = false
    private var runOnes = true

    internal val mIsolatedServiceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(componentName: ComponentName, iBinder: IBinder) {
            serviceBinder = IIsolatedService.Stub.asInterface(iBinder)
            bServiceBound = true
            if (runOnes) {
                runOnes = false
                runRootDevice()
            }
        }

        override fun onServiceDisconnected(componentName: ComponentName) {
            bServiceBound = false
        }
    }

    //region HANDLER
    fun runRootDevice() {
        if (bServiceBound) {
            var bIsMagisk = false
            try {
                serviceBinder?.isMagiskPresent()?.let { bIsMagisk = it }
                if (bIsMagisk) throw RuntimeException(Constants.MAGISK_FOUND)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
    }

    fun setCanRecallAPI(value: Boolean) = isCanRecallAPI.set(value)

    fun setRefreshTokenCalling(value: Boolean) = isRefreshTokenCalling.set(value)

    //endregion
}