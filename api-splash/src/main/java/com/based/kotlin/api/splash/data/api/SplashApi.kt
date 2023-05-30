package com.based.kotlin.api.splash.data.api

import com.based.kotlin.api.splash.util.SplashUrlConstants.URL_POST_CHECK_CONFIG
import com.based.kotlin.core.entity.splash.ConfigCheckRequest
import com.based.kotlin.core.entity.splash.ConfigCheckResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface SplashApi {
    //region POST
    @POST(URL_POST_CHECK_CONFIG)
    suspend fun postCheckConfig(
        @Body request: ConfigCheckRequest
    ): Response<ConfigCheckResponse>
    //endregion
}