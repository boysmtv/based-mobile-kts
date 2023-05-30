package com.based.kotlin.core.di

import android.annotation.SuppressLint
import android.content.Context
import com.based.kotlin.core.BuildConfig.API_CONNECT_TIMEOUT
import com.based.kotlin.core.BuildConfig.API_READ_TIMEOUT
import com.based.kotlin.core.BuildConfig.API_WRITE_TIMEOUT
import com.based.kotlin.core.BuildConfig.BASE_URL
import com.based.kotlin.core.BuildConfig.CERTIFICATE_PUBLIC_KEY
import com.based.kotlin.core.BuildConfig.ENABLE_API_LOG
import com.based.kotlin.core.BuildConfig.ENABLE_TRUSTED_BASE_URL
import com.based.kotlin.core.BuildConfig.ENABLE_TRUST_MANAGER
import com.based.kotlin.core.R
import com.based.kotlin.core.common.util.AmountAdapter
import com.based.kotlin.core.common.util.AppVersion.VERSION_CODE
import com.based.kotlin.core.common.util.AppVersion.VERSION_NAME
import com.based.kotlin.core.common.util.BigDecimalAdapter
import com.based.kotlin.core.common.util.NetworkConstants
import com.based.kotlin.core.common.util.NetworkConstants.APP_NAME
import com.based.kotlin.core.common.util.NetworkConstants.APP_VERSION
import com.based.kotlin.core.common.util.NetworkConstants.CERTIFICATE_TYPE
import com.based.kotlin.core.common.util.NetworkConstants.CONTENT_TYPE
import com.based.kotlin.core.common.util.NetworkConstants.JSON_TYPE
import com.based.kotlin.core.common.util.NetworkConstants.PINS_SUFFIX
import com.based.kotlin.core.common.util.NetworkConstants.TLS_PROTOCOL
import com.based.kotlin.core.common.util.NetworkConstants.USER_AGENT
import com.based.kotlin.core.remote.AuthInterceptor
import com.based.kotlin.utilities.constants.Constants.PIPE_DELIMITER
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.CertificatePinner
import okhttp3.ConnectionSpec
import okhttp3.ConnectionSpec.Companion.COMPATIBLE_TLS
import okhttp3.ConnectionSpec.Companion.MODERN_TLS
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.OkHttpClient
import okhttp3.sse.EventSource
import okhttp3.sse.EventSources
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.cert.CertificateException
import java.security.cert.CertificateFactory
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
@Suppress("MaxLineLength")
class NetworkModule {
    private val baseUrl = BASE_URL

    @Provides
    @Singleton
    internal fun provideCertificatePinner(): CertificatePinner {
        val baseUrl = BASE_URL.toHttpUrl()
        val certificatePinnerBuilder = CertificatePinner.Builder()
        val publicKeys = CERTIFICATE_PUBLIC_KEY.split(PIPE_DELIMITER)
        publicKeys.forEach { certificatePinnerBuilder.add(baseUrl.host, it.plus(PINS_SUFFIX)) }
        return certificatePinnerBuilder.build()
    }

    @Provides
    @Singleton
    internal fun provideTrustManager(@ApplicationContext app: Context): Array<X509TrustManager> =
        arrayOf(@SuppressLint("CustomX509TrustManager")
        object : X509TrustManager {
            @Throws(CertificateException::class)
            override fun checkClientTrusted(chain: Array<X509Certificate>, authType: String) {
                try {
                    chain.first().checkValidity()
                } catch (e: CertificateException) {
                    throw e
                }
            }

            @Throws(CertificateException::class)
            override fun checkServerTrusted(chain: Array<X509Certificate>, authType: String) {
                try {
                    chain.first().checkValidity()
                } catch (e: CertificateException) {
                    throw e
                }
            }

            override fun getAcceptedIssuers(): Array<X509Certificate> {
                val caInputStream = app.resources.openRawResource(R.raw.root_ca)
                val certificateFactory = CertificateFactory.getInstance(CERTIFICATE_TYPE)
                return arrayOf(caInputStream.use { certificateFactory.generateCertificate(it) as X509Certificate })
            }
        })

    @Provides
    @Singleton
    internal fun provideSslSocketFactory(trustManagers: Array<out X509TrustManager>): SSLSocketFactory =
        SSLContext.getInstance(TLS_PROTOCOL).apply { init(null, trustManagers, null) }.socketFactory

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().add(AmountAdapter())
        .add(KotlinJsonAdapterFactory())
        .add(BigDecimalAdapter())
        .build()

    @Provides
    @Singleton
    fun provideBDSApi(moshi: Moshi, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext app: Context,
        trustManagers: Array<out X509TrustManager>,
        sslSocketFactory: SSLSocketFactory,
        certificatePinner: CertificatePinner,
        authHeader: AuthInterceptor
    ): OkHttpClient {

        val connectionSpecs =
            mutableListOf(MODERN_TLS, COMPATIBLE_TLS).apply {
                if (!ENABLE_TRUSTED_BASE_URL) add(ConnectionSpec.CLEARTEXT)
            }

        return OkHttpClient.Builder()
            .connectTimeout(API_CONNECT_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .writeTimeout(API_WRITE_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .readTimeout(API_READ_TIMEOUT.toLong(), TimeUnit.MILLISECONDS)
            .connectionSpecs(connectionSpecs)
            .addInterceptor { chain ->
                chain.request().newBuilder()
                    .addHeader(
                        USER_AGENT, "$APP_NAME(${VERSION_NAME}-${VERSION_CODE}) " +
                                System.getProperty(NetworkConstants.HTTP_AGENT).orEmpty()
                    )
                    .addHeader(CONTENT_TYPE, JSON_TYPE)
                    .addHeader(APP_VERSION, VERSION_NAME)
                    .build()
                    .let(chain::proceed)
            }
            .addInterceptor(authHeader)
            .hostnameVerifier { _, _ -> true }
            .apply {
                /**
                 * Remove Certificate Injection on Production [TRUST_MANAGER = false]
                 */
                if (ENABLE_TRUST_MANAGER) sslSocketFactory(
                    sslSocketFactory,
                    trustManagers.first()
                )
                if (ENABLE_TRUSTED_BASE_URL) certificatePinner(certificatePinner)
                if (ENABLE_API_LOG) addInterceptor(
                    ChuckerInterceptor.Builder(app.applicationContext).build()
                )
            }.build()
    }

    @Provides
    @Singleton
    fun provideEventSource(okHttpClient: OkHttpClient): EventSource.Factory =
        EventSources.createFactory(okHttpClient.newBuilder().build())
}