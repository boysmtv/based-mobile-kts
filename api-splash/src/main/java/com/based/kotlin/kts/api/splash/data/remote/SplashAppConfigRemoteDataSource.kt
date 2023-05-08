///*
// * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
// *
// * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
// * Proprietary and confidential
// *
// */
//package com.based.kotlin.kts.api.splash.data.remote
//
//import com.based.kotlin.kts.api.splash.data.api.SplashApi
//import com.based.kotlin.kts.core.base.BaseDataSource
//import com.based.kotlin.kts.core.entity.splash.ConfigCheckRequest
//import javax.inject.Inject
//
//class SplashAppConfigRemoteDataSource @Inject constructor(var api: SplashApi) : BaseDataSource() {
//
//    //region POST
//    suspend fun postConfigCheck(param: ConfigCheckRequest) = getResultWithSingleObject {
//        api.postCheckConfig(param)
//    }
//    //endregion
//}