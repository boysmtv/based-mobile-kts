package com.based.kotlin.kts.api.authentication.data.repository

import com.based.kotlin.kts.api.authentication.data.local.AuthLocalDataSource
import com.based.kotlin.kts.api.authentication.data.remote.AuthRemoteDataSource
import com.based.kotlin.kts.core.data.CoroutineDispatcherProvider
import com.based.kotlin.kts.core.entity.auth.AuthRequest
import com.based.kotlin.kts.core.data.resultFlow
import com.based.kotlin.kts.core.entity.auth.AuthIdentityRequest
import javax.inject.Inject

class AuthRepository @Inject constructor(
    private val remoteDataSource: AuthRemoteDataSource,
    internal val localDataSource: AuthLocalDataSource,
    private val dispatcher: CoroutineDispatcherProvider
) {

    fun postLogin(authRequest: AuthRequest) = resultFlow(
        networkCall = { remoteDataSource.postLogin(authRequest) },
        saveCallResult = {
            localDataSource.saveLoginData(it)
            localDataSource.saveUserId(authRequest.userId.orEmpty())
        },
        dispatcher = dispatcher
    )

    fun postLogout() = resultFlow(
        networkCall = { remoteDataSource.postLogout() },
        dispatcher = dispatcher
    )

    //region GET
    fun getRefreshTokenIdentity() = resultFlow(
        networkCall = {
            remoteDataSource.getRefreshTokenIdentity(
                AuthIdentityRequest(localDataSource.loadRefreshToken())
            )
        },
        saveCallResult = {
            localDataSource.saveAccessToken(it.accessToken, it.refreshToken)
            it.validity.let { validity ->
                localDataSource
                    .saveRefreshTokenTimestamp(validity.toLong())
            }
        },
        dispatcher = dispatcher
    )
    //endregion

}