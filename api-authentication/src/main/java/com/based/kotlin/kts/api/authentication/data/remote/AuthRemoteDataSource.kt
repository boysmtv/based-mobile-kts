package com.based.kotlin.kts.api.authentication.data.remote

import com.based.kotlin.kts.api.authentication.data.api.AuthApi
import com.based.kotlin.kts.core.entity.auth.AuthRequest
import com.based.kotlin.kts.core.base.BaseDataSource
import com.based.kotlin.kts.core.entity.auth.AuthIdentityRequest
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