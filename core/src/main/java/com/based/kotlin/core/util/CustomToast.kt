/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.core.util

import android.app.Activity
import android.view.Gravity.TOP
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import com.based.kotlin.core.R
import com.based.kotlin.utilities.constants.Constants.FORTY
import com.based.kotlin.utilities.constants.Constants.ZERO

fun Toast.showCustomToastFailed(message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate(
        R.layout.toast_failed,
        activity.findViewById(R.id.toast_container)
    )
    val textView = layout.findViewById<TextView>(R.id.toast_message)

    textView.text = message
    this.apply {
        setGravity(TOP, ZERO, FORTY)
        duration = LENGTH_LONG
        view = layout
        show()
    }
}

fun Toast.showSuccess(message: String, activity: Activity) {
    val layout = activity.layoutInflater.inflate(
        R.layout.toast_success,
        activity.findViewById(R.id.toast_container_success)
    )
    val textView = layout.findViewById<TextView>(R.id.toast_message_success)

    println("message $message")
    textView.text = message
    this.apply {
        setGravity(TOP, ZERO, FORTY)
        duration = LENGTH_LONG
        view = layout
        show()
    }
}
