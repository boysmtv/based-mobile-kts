package com.based.kotlin.core.util

import android.content.Context
import android.content.Context.WIFI_SERVICE
import android.net.wifi.WifiManager
import com.based.kotlin.utilities.constants.Constants.EIGHT
import com.based.kotlin.utilities.constants.Constants.IP_TRANSFORM_FORMAT
import com.based.kotlin.utilities.constants.Constants.SIX_TEEN
import com.based.kotlin.utilities.constants.Constants.TWENTY_FOUR
import com.based.kotlin.utilities.constants.Constants.WIFI_ADDRESS_FORMAT
import java.util.Locale

object IpAddressUtils {
    fun getWifiIpAddress(context: Context): String {
        val wm = context.applicationContext.getSystemService(WIFI_SERVICE) as WifiManager
        val wifiInfo = wm.connectionInfo.ipAddress

        return java.lang.String.format(
            Locale.US, WIFI_ADDRESS_FORMAT,
            wifiInfo and IP_TRANSFORM_FORMAT,
            wifiInfo shr EIGHT and IP_TRANSFORM_FORMAT,
            wifiInfo shr SIX_TEEN and IP_TRANSFORM_FORMAT,
            wifiInfo shr TWENTY_FOUR and IP_TRANSFORM_FORMAT
        )
    }
}


