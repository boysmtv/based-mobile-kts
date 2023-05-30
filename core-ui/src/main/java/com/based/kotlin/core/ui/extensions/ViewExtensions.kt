package com.based.kotlin.core.ui.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.net.Uri
import android.net.http.SslError
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.webkit.SslErrorHandler
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.CheckBox
import android.widget.FrameLayout
import androidx.annotation.StringRes
import com.based.kotlin.core.ui.listener.SingleClickListener
import com.based.kotlin.core.ui.listener.SingleClickListener.Companion.THROTTLE_INTERVAL
import com.based.kotlin.utilities.constants.Constants
import com.based.kotlin.utilities.constants.Constants.ALPHA.OPACITY_NORMAL
import com.based.kotlin.utilities.constants.Constants.ALPHA.OPACITY_ZERO
import com.based.kotlin.utilities.constants.Constants.CountDown.ONE_THOUSAND
import com.based.kotlin.utilities.constants.Constants.FIVE_HUNDRED

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.animateShow() {
    this.apply {
        alpha = OPACITY_ZERO
        visibility = View.VISIBLE
    }
    this.animate()
        .alpha(OPACITY_NORMAL)
        .setDuration(ONE_THOUSAND.toLong())
        .setListener(null)
}

fun View.animateSlide(translationValue: Float, view: Int) {
    this.animate()
        .translationY(translationValue)
        .setDuration(FIVE_HUNDRED.toLong())
        .setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator?) {
                this@animateSlide.visibility = view
            }
        })
}

fun CheckBox.checked() {
    this.isChecked = true
}

fun CheckBox.unChecked() {
    this.isChecked = false
}

@Deprecated(
    message = "Use Android-KTX built-in function instead",
    replaceWith = ReplaceWith("androidx.core.view.isVisible")
)
fun View.showOrHide(condition: Boolean) {
    if (condition) this.visibility = View.VISIBLE
    else this.visibility = View.GONE
}

@Deprecated(
    message = "Use Android-KTX built-in function instead",
    replaceWith = ReplaceWith("androidx.core.view.isInvisible")
)
fun View.showOrInvisible(condition: Boolean) {
    if (condition) this.visibility = View.VISIBLE
    else this.visibility = View.INVISIBLE
}

fun View.showForceKeyboard() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_FORCED)
}

fun View.hideKeyboard() {
    val inputMethodManager =
        context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    if (inputMethodManager.isAcceptingText) inputMethodManager.hideSoftInputFromWindow(
        windowToken,
        0
    )
}

fun withRecycle(attributes: TypedArray, consumeAttributes: TypedArray.() -> Unit) {
    try {
        consumeAttributes(attributes)
    } finally {
        attributes.recycle()
    }
}

@SuppressLint("SetJavaScriptEnabled")
fun WebView.setupWebView(
    onUrlIntercepted: ((uri: Uri?) -> Boolean)? = null,
    onLoadError: ((WebResourceRequest?, WebResourceError?) -> Unit)? = null,
    onLoadCompleted: () -> Unit = {}
) {
    val chromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView?, newProgress: Int) {
            super.onProgressChanged(view, newProgress)
            if (newProgress == Constants.Tnc.WEB_MAX_PROGRESS) onLoadCompleted()
        }
    }

    webViewClient = object : WebViewClient() {
        override fun onReceivedSslError(
            view: WebView?,
            handler: SslErrorHandler?,
            error: SslError?
        ) {
            handler?.proceed()
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?) =
            onUrlIntercepted?.let { it(request?.url) } ?: false

        @Deprecated(
            "Deprecated in Java",
            ReplaceWith("onUrlIntercepted?.let { it(Uri.parse(url)) } ?: false", "android.net.Uri")
        )
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?) =
            onUrlIntercepted?.let {
                it(Uri.parse(url))
            } ?: false

        override fun onReceivedError(
            view: WebView?,
            request: WebResourceRequest?,
            error: WebResourceError?
        ) {
            super.onReceivedError(view, request, error)
            onLoadError?.invoke(request, error)
        }
    }

    webChromeClient = chromeClient
    settings.javaScriptEnabled = true
    settings.loadsImagesAutomatically = true
    isHorizontalScrollBarEnabled = false
    isScrollbarFadingEnabled = false
    isVerticalScrollBarEnabled = false
}

fun View.getString(@StringRes stringId: Int): String = context.getString(stringId)

fun View.setOnSingleClickListener(
    throttleInterval: Long = THROTTLE_INTERVAL,
    click: View.OnClickListener?
) {
    click?.let { setOnSingleClick(throttleInterval, click) } ?: setOnClickListener(null)
}

fun View.setOnSingleClickListener(throttleInterval: Long, listener: (View) -> Unit) {
    this.setOnSingleClickListener(throttleInterval, View.OnClickListener { v -> listener(v) })
}

fun View.setOnSingleClick(throttleInterval: Long, click: View.OnClickListener) =
    setOnClickListener(SingleClickListener(throttleInterval, click))


fun FrameLayout.alreadyScrolled(listener: (Boolean) -> Unit) {
    post { listener(!canScrollVertically(Constants.ONE)) }
    setOnScrollChangeListener { _, _, _, _, _ ->
        canScrollVertically(Constants.ONE).run { if (!this) listener(true) }
    }
}