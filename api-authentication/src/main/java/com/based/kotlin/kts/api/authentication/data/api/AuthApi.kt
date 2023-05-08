package com.based.kotlin.kts.api.authentication.data.api

import com.based.kotlin.kts.core.entity.auth.AuthRequest
import com.based.kotlin.kts.core.entity.auth.AuthResponse
import com.based.kotlin.kts.core.entity.auth.LogoutResponse
import com.based.kotlin.kts.api.authentication.util.AuthUrlConstants.POST_URL_GENERAL_BANKER_LOGIN
import com.based.kotlin.kts.api.authentication.util.AuthUrlConstants.POST_URL_GENERAL_BANKER_LOGOUT
import com.based.kotlin.kts.api.authentication.util.AuthUrlConstants.POST_URL_GENERAL_BANKER_REFRESH_TOKEN
import com.based.kotlin.kts.core.entity.auth.AuthIdentity
import com.based.kotlin.kts.core.entity.auth.AuthIdentityRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST(POST_URL_GENERAL_BANKER_LOGIN)
    suspend fun postLogin(@Body authRequest: AuthRequest): Response<AuthResponse>

    @POST(POST_URL_GENERAL_BANKER_LOGOUT)
    suspend fun postLogout(): Response<LogoutResponse>

    @POST(POST_URL_GENERAL_BANKER_REFRESH_TOKEN)
    suspend fun postRefreshTokenIdentity(@Body request: AuthIdentityRequest): Response<AuthIdentity>

}