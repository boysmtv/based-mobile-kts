package com.based.kotlin.feature.splash.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.based.kotlin.api.splash.domain.load.LoadAppVersionUseCase
import com.based.kotlin.api.splash.domain.post.PostCheckConfigUseCase
import com.based.kotlin.api.splash.domain.remove.RemoveContextCacheUseCase
import com.based.kotlin.api.splash.domain.save.SaveAppVersionUseCase
import com.based.kotlin.api.splash.domain.save.SaveIpAppUseCase
import com.based.kotlin.core.base.BaseViewModel
import com.based.kotlin.core.common.entity.Result
import com.based.kotlin.core.common.navigation.DeeplinkNavigation
import com.based.kotlin.core.common.util.AppVersion
import com.based.kotlin.core.entity.splash.ConfigCheckRequest
import com.based.kotlin.core.entity.splash.ConfigCheckResponse
import com.based.kotlin.feature.splash.util.SplashConstants.CHANNEL_ID
import com.based.kotlin.utilities.constants.AuthenticationDeeplink.NAVIGATE_TO_AUTHENTICATION
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val postCheckConfigUseCase: PostCheckConfigUseCase,
    private val saveIPAppUseCase: SaveIpAppUseCase,
    private val removeContextCacheUseCase : RemoveContextCacheUseCase,
    private val saveAppVersionUseCase: SaveAppVersionUseCase,
    private val loadAppVersionUseCase: LoadAppVersionUseCase,
    private val deeplinkNavigation: DeeplinkNavigation
) : BaseViewModel() {

    private val _checkConfig = MutableLiveData<Result<ConfigCheckResponse>>()
    val checkConfig: LiveData<Result<ConfigCheckResponse>> = _checkConfig

    suspend fun postConfigCheck() =
        postCheckConfigUseCase(
            ConfigCheckRequest(CHANNEL_ID)
        ).collect { _checkConfig.postValue(it) }

    fun saveIp(ip: String) = saveIPAppUseCase(ip)

    fun checkAppVersion(){
        if (!loadAppVersionUseCase().equals(AppVersion.VERSION_NAME)){
            removeContextCacheUseCase()
            saveAppVersionUseCase(AppVersion.VERSION_NAME)
        }
    }

    fun navigateToAuthentication(){
        deeplinkNavigation.navigate(
            NAVIGATE_TO_AUTHENTICATION
        )
    }
}