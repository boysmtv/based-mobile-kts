package com.based.kotlin.core.util

import android.annotation.TargetApi
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.CONNECTIVITY_SERVICE
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities.TRANSPORT_CELLULAR
import android.net.NetworkCapabilities.TRANSPORT_ETHERNET
import android.net.NetworkCapabilities.TRANSPORT_VPN
import android.net.NetworkCapabilities.TRANSPORT_WIFI
import android.net.NetworkCapabilities.TRANSPORT_WIFI_AWARE
import android.net.NetworkInfo
import android.net.NetworkRequest
import android.os.Build
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.based.kotlin.utilities.constants.Constants.DELAY_MILLIS_ONE_SECOND

class NetworkConnectionLiveData(val context: Context) : LiveData<Boolean>() {

    private var connectivityManager: ConnectivityManager =
        context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var connectivityManagerCallback: ConnectivityManager.NetworkCallback

    override fun onActive() {
        super.onActive()
        updateConnection()
        when {
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> connectivityManager.registerDefaultNetworkCallback(
                getConnectivityManagerCallback()
            )

            Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> lollipopNetworkAvailableRequest()
            else -> {
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    context.registerReceiver(networkReceiver, IntentFilter(CONNECTIVITY_ACTION))
                }
            }
        }
    }

    override fun onInactive() {
        super.onInactive()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            connectivityManager.unregisterNetworkCallback(connectivityManagerCallback)
        } else {
            context.unregisterReceiver(networkReceiver)
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkAvailableRequest() {
        val builder = NetworkRequest.Builder()
            .addTransportType(TRANSPORT_CELLULAR)
            .addTransportType(TRANSPORT_WIFI)
            .addTransportType(TRANSPORT_ETHERNET)
            .addTransportType(TRANSPORT_VPN)
            .addTransportType(TRANSPORT_WIFI_AWARE)
        connectivityManager.registerNetworkCallback(
            builder.build(),
            getConnectivityManagerCallback()
        )
    }

    private fun getConnectivityManagerCallback(): ConnectivityManager.NetworkCallback {
        connectivityManagerCallback = object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                postValue(true)
            }

            override fun onLost(network: Network) {
                postValue(false)
            }
        }
        return connectivityManagerCallback
    }

    private val networkReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            updateConnection()
        }
    }

    private fun updateConnection() {
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        postValue(activeNetwork?.isConnected == true)
    }

    companion object {
        const val CONNECTIVITY_ACTION = "android.net.conn.CONNECTIVITY_CHANGE"
    }
}

fun <T> LiveData<T>.debounce(duration: Long = DELAY_MILLIS_ONE_SECOND) =
    MediatorLiveData<T>().also {
        val handler = Handler(Looper.getMainLooper())
        val runnable = Runnable { it.value = this.value }
        it.addSource(this) {
            handler.removeCallbacks(runnable)
            handler.postDelayed(runnable, duration)
        }
    }