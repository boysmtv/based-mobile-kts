/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.api.splash.data.local

import com.based.kotlin.core.entity.splash.AppConfig
import javax.inject.Inject

class SplashAppConfigLocalDataSource @Inject constructor(
    private val cache: SplashAppConfigCache
) {
    //region LOAD
    fun loadAppConfigCache(key: String) = cache.get(key)

    fun loadAllAppConfigCache() = cache.getAll()

    fun loadAppVersion() = cache.loadAppVersion()
    //endregion

    //region RESET
    fun resetConfigCache() = cache.resetConfigCache()
    //endregion

    //region SAVE
    fun saveAppConfigCache(appConfig: List<AppConfig>) =
        cache.set(appConfig)

    fun saveAppVersion(version: String) = cache.saveAppVersion(version)
    //endregion
}