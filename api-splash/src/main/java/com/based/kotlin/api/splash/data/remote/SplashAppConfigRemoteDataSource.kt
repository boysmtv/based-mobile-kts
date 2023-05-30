package com.based.kotlin.api.splash.data.remote

import com.based.kotlin.api.splash.data.api.SplashApi
import com.based.kotlin.core.base.BaseDataSource
import com.based.kotlin.core.entity.splash.ConfigCheckRequest
import javax.inject.Inject

class SplashAppConfigRemoteDataSource @Inject constructor(var api: SplashApi) : BaseDataSource() {

    //region POST
    suspend fun postConfigCheck(param: ConfigCheckRequest) = getResultWithSingleObject {
        api.postCheckConfig(param)
    }
    //endregion
}