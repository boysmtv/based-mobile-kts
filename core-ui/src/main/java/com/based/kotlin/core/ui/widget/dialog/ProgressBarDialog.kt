/*
 * Copyright © 2022 PT Bank Mandiri (Persero) Tbk.
 *
 * Unauthorized copying, publishing of this file, via any medium is strictly prohibited
 * Proprietary and confidential
 *
 */
package com.based.kotlin.core.ui.widget.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import androidx.fragment.app.DialogFragment
import com.based.kotlin.core.ui.R

class ProgressBarDialog : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        val lp = dialog.window?.attributes
        lp?.gravity = Gravity.CENTER

        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.attributes = lp
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)

        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(false)

        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.dialog_progressbar, container, false)
    }
}