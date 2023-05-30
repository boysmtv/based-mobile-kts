package com.based.kotlin.api.splash.data.repository

import com.based.kotlin.api.splash.data.local.SplashAppConfigLocalDataSource
import com.based.kotlin.api.splash.data.local.SplashAppConfigResource
import com.based.kotlin.api.splash.data.local.SplashIpAppLocalDataSource
import com.based.kotlin.api.splash.data.local.SplashLanguagePackCache
import com.based.kotlin.api.splash.data.local.SplashLovConfigLocalDataSource
import com.based.kotlin.api.splash.data.remote.SplashAppConfigRemoteDataSource
import com.based.kotlin.core.data.resultFlow
import com.based.kotlin.core.entity.splash.ConfigCheckRequest
import com.based.kotlin.utilities.constants.Constants.ZERO
import javax.inject.Inject

class SplashRepository @Inject constructor(
    internal val appConfigLocalDataSource: SplashAppConfigLocalDataSource,
    internal val lovConfigLocalDataSource: SplashLovConfigLocalDataSource,
    private val appConfigRemoteDataSource: SplashAppConfigRemoteDataSource,
    private val splashLanguagePackCache: SplashLanguagePackCache,
    private val appConfigResource: SplashAppConfigResource,
    private val ipAppLocalDataSource: SplashIpAppLocalDataSource
) {
    //region POST
    fun postCheckConfig(param: ConfigCheckRequest) = resultFlow(
        networkCall = { appConfigRemoteDataSource.postConfigCheck(param) },
        saveCallResult = {
            it.lovData?.let { lovResult ->
                lovResult.lov?.let { lov -> lovConfigLocalDataSource.setLov(lov) }
            }
            it.parameterData?.let { config -> appConfigLocalDataSource.saveAppConfigCache(config.parameters) }
            it.localization?.let { locKey ->
                splashLanguagePackCache.set(locKey)
            }
        }
    )
    //endregion

    //region LOAD
    fun loadAppConfig(key: String) = appConfigLocalDataSource.loadAppConfigCache(key = key)

    fun loadAllAppConfig() = appConfigLocalDataSource.loadAllAppConfigCache().let {
        if (it.size == ZERO) appConfigResource.get() else it
    }?.also { appConfigLocalDataSource.saveAppConfigCache(it) }
    //endregion

    //region SAVE
    fun saveIp(ip: String) = ipAppLocalDataSource.saveIpCache(ip)
    //endregion
}