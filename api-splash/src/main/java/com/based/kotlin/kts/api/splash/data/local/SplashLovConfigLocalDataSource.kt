///*
// * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
// *
// * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
// * Proprietary and confidential
// *
// */
//package com.based.kotlin.kts.api.splash.data.local
//
//import com.based.kotlin.kts.core.entity.dashboard.Lov
//import javax.inject.Inject
//
//class SplashLovConfigLocalDataSource @Inject constructor(
//    private val cache: SplashLovConfigCache
//) {
//    //region GET
//    fun getLov() = cache.getLov()
//
//    fun getLovByKey(key : String) = cache.getLovByKey(key)
//    //endregion
//
//    //region SET
//    fun setLov(list: List<Lov>) = cache.setLov(list)
//    //endregion
//}