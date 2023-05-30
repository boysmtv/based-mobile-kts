package com.based.kotlin.core.util

import android.graphics.Color
import android.graphics.Typeface
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.based.kotlin.core.ui.extensions.hide
import com.based.kotlin.core.ui.extensions.show
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.utilities.constants.Constants.MINUS_1

fun View.getIntDimen(@DimenRes dimenId: Int): Int = resources.getDimension(dimenId).toInt()

fun View.enable() {
    isEnabled = true
}

fun View.disable() {
    isEnabled = false
}

fun View.enable(isEnable: Boolean) {
    isEnabled = isEnable
}

fun View.getString(@StringRes stringId: Int): String = context.getString(stringId)

val View.viewLayoutInflater: LayoutInflater get() = LayoutInflater.from(context)

fun TextView.showText(text: CharSequence) {
    if (text.isEmpty()) visibility = View.GONE
    else {
        visibility = View.VISIBLE
        setText(text)
    }
}

fun TextView.showText(isVisible: Boolean, text: CharSequence) {
    showText(if (isVisible) text else EMPTY_STRING)
}

fun View.show(isVisible: Boolean) {
    if (isVisible) show() else hide()
}

fun TextView.setClickableText(
    text: SpannableString,
    clickableText: String,
    clickableColor: Int,
    isUnderlineText: Boolean = true,
    clickableTextTypeface: Typeface,
    clickListener: () -> Unit
) {
    val startingPosition: Int = text.indexOf(clickableText)

    if (startingPosition > MINUS_1) {
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                clickListener()
            }

            override fun updateDrawState(textPaint: TextPaint) {
                super.updateDrawState(textPaint)
                textPaint.isUnderlineText = isUnderlineText
                textPaint.typeface = clickableTextTypeface
                textPaint.color = resources.getColor(clickableColor, null)
            }
        }

        val endingPosition: Int = startingPosition + clickableText.length
        text.setSpan(
            clickableSpan, startingPosition,
            endingPosition, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        movementMethod = LinkMovementMethod.getInstance()
        highlightColor = Color.TRANSPARENT
    }

    setText(text)
}