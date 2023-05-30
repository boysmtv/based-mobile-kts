package com.based.kotlin.api.splash.data.local

import com.based.kotlin.core.entity.util.Lov
import javax.inject.Inject

class SplashLovConfigLocalDataSource @Inject constructor(
    private val cache: SplashLovConfigCache
) {
    //region GET
    fun getLov() = cache.getLov()

    fun getLovByKey(key: String) = cache.getLovByKey(key)
    //endregion

    //region SET
    fun setLov(list: List<Lov>) = cache.setLov(list)
    //endregion
}