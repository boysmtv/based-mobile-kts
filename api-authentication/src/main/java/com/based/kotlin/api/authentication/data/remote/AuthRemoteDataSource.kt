package com.based.kotlin.api.authentication.data.remote

import com.based.kotlin.api.authentication.data.api.AuthApi
import com.based.kotlin.core.base.BaseDataSource
import com.based.kotlin.core.entity.auth.AuthIdentityRequest
import com.based.kotlin.core.entity.auth.AuthRequest
import javax.inject.Inject

class AuthRemoteDataSource @Inject constructor(var api: AuthApi) : BaseDataSource() {

    suspend fun postLogin(authRequest: AuthRequest) = getResultWithSingleObject {
        api.postLogin(authRequest)
    }

    suspend fun postLogout() = getResultWithSingleObject {
        api.postLogout()
    }

    suspend fun getRefreshTokenIdentity(request: AuthIdentityRequest) =
        getResultWithSingleObject { api.postRefreshTokenIdentity(request) }

}