///*
// * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
// *
// * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
// * Proprietary and confidential
// *
// */
//package com.based.kotlin.kts.api.splash.data.repository
//
//import com.based.kotlin.kts.api.splash.data.local.SplashAppConfigLocalDataSource
//import com.based.kotlin.kts.api.splash.data.local.SplashAppConfigResource
//import com.based.kotlin.kts.api.splash.data.local.SplashIpAppLocalDataSource
//import com.based.kotlin.kts.api.splash.data.local.SplashLovConfigLocalDataSource
//import com.based.kotlin.kts.api.splash.data.local.SplashLanguagePackCache
//import com.based.kotlin.kts.api.splash.data.remote.SplashAppConfigRemoteDataSource
//import com.based.kotlin.kts.utilities.constants.Constants.ZERO
//import com.based.kotlin.kts.core.data.resultFlow
//import com.based.kotlin.kts.core.entity.splash.ConfigCheckRequest
//import javax.inject.Inject
//
//class SplashRepository @Inject constructor(
//    internal val appConfigLocalDataSource: SplashAppConfigLocalDataSource,
//    internal val lovConfigLocalDataSource: SplashLovConfigLocalDataSource,
//    private val appConfigRemoteDataSource: SplashAppConfigRemoteDataSource,
//    private val splashLanguagePackCache: SplashLanguagePackCache,
//    private val appConfigResource: SplashAppConfigResource,
//    private val ipAppLocalDataSource: SplashIpAppLocalDataSource
//) {
//    //region POST
//    fun postCheckConfig(param: ConfigCheckRequest) = resultFlow(
//        networkCall = { appConfigRemoteDataSource.postConfigCheck(param) },
//        saveCallResult = {
//            it.lovData?.let { lovResult ->
//                lovResult.lov?.let { lov -> lovConfigLocalDataSource.setLov(lov) }
//            }
//            it.parameterData?.let { config -> appConfigLocalDataSource.saveAppConfigCache(config.parameters) }
//            it.localization?.let { locKey ->
//                splashLanguagePackCache.set(locKey)
//            }
//        }
//    )
//    //endregion
//
//    //region LOAD
//    fun loadAppConfig(key: String) = appConfigLocalDataSource.loadAppConfigCache(key = key)
//
//    fun loadAllAppConfig() = appConfigLocalDataSource.loadAllAppConfigCache().let {
//        if (it.size == ZERO) appConfigResource.get() else it
//    }?.also { appConfigLocalDataSource.saveAppConfigCache(it) }
//    //endregion
//
//    //region SAVE
//    fun saveIp(ip: String) = ipAppLocalDataSource.saveIpCache(ip)
//    //endregion
//}