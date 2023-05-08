///*
// * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
// *
// * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
// * Proprietary and confidential
// *
// */
//package com.based.kotlin.kts.api.splash.data.local
//
//import com.based.kotlin.kts.core.common.util.CorePlainPrefManager
//import com.based.kotlin.kts.core.common.util.JsonUtil
//import com.based.kotlin.kts.core.common.util.PreferenceConstants
//import com.based.kotlin.kts.core.common.util.security.SecurePrefManager
//import com.based.kotlin.kts.core.entity.dashboard.Lov
//import com.based.kotlin.kts.core.entity.dashboard.LovResult
//import javax.inject.Inject
//
//class SplashLovConfigCache @Inject constructor(
//    private val securePrefManager: SecurePrefManager,
//    private val plainPrefManager: CorePlainPrefManager,
//    private val jsonUtil: JsonUtil
//) {
//    //region GET
//    fun getLovByKey (key : String): List<Lov>? = getLov()?.filter { it.type?.trim() == key }
//
//    fun getLov(): List<Lov>? = securePrefManager.getString(
//        PreferenceConstants.AppConfig.PREF_KEY_LOV)?.let {
//        jsonUtil.fromJson<LovResult>(it)?.lov
//    }
//    //endregion
//
//    //region SET
//    fun setLov(list: List<Lov>){
//        securePrefManager.setString(
//            PreferenceConstants.AppConfig.PREF_KEY_LOV,
//            jsonUtil.toJson(
//                LovResult(
//                    lov = list
//                )
//            )
//        )
//    }
//    //endregion
//}