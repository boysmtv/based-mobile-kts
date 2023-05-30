package com.based.kotlin.core.remote

import com.based.kotlin.core.common.util.NetworkConstants.AUTHORIZATION
import com.based.kotlin.core.common.util.NetworkConstants.BEARER
import com.based.kotlin.core.common.util.NetworkConstants.HTTP_CLIENT_IP
import com.based.kotlin.core.common.util.NetworkConstants.LOGIN
import com.based.kotlin.core.common.util.security.SecurePrefManager
import com.based.kotlin.utilities.constants.PreferenceConstants.Auth.PREF_KEY_TOKEN
import com.based.kotlin.utilities.constants.PreferenceConstants.Splash.PREF_KEY_IP_APP
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Inject

class AuthInterceptor  @Inject constructor (private val securePrefManager: SecurePrefManager): Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        if (!request.url.encodedPath.contains(LOGIN, ignoreCase = false)) {
            request = chain.request().newBuilder().addHeader(
                AUTHORIZATION,
                BEARER.plus(
                    securePrefManager.getString(PREF_KEY_TOKEN).orEmpty()
                )
            ).addHeader(
                HTTP_CLIENT_IP,
                securePrefManager.getString(PREF_KEY_IP_APP).orEmpty()
            ).build()
        }
        return chain.proceed(request)
    }
}
