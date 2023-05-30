package com.based.kotlin.core.ui.widget.error

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.core.view.isVisible
import com.based.kotlin.core.ui.R
import com.based.kotlin.core.ui.databinding.ContentErrorViewBinding
import com.based.kotlin.core.ui.extensions.hide
import com.based.kotlin.core.ui.extensions.invisible
import com.based.kotlin.core.ui.extensions.show
import com.based.kotlin.utilities.constants.Constants.ONE_HUNDRED_FIFTY
import com.based.kotlin.utilities.constants.Constants.ZERO

class ErrorView @JvmOverloads constructor(
    ctx: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = ZERO
) : LinearLayout(ctx, attrs, defStyleAttr) {

    val binding = ContentErrorViewBinding.inflate(LayoutInflater.from(ctx), this, true)

    init {
        context.theme.obtainStyledAttributes(attrs, R.styleable.ErrorView, defStyleAttr, ZERO)
            .apply {
                try {
                    setSizeImage(
                        getDimension(R.styleable.ErrorView_layout_image_width, ONE_HUNDRED_FIFTY),
                        getDimension(R.styleable.ErrorView_layout_image_height, ONE_HUNDRED_FIFTY)
                    )
                } finally {
                    recycle()
                }
            }
    }

    fun setImage(drawable: Drawable?) = with(binding) {
        ivError.setImageDrawable(drawable)
    }

    fun setMessage(message: String) = with(binding) {
        tvMessageError.text = message
    }

    fun setTitle(title: String) = with(binding) {
        tvTitleError.text = title
    }

    fun setBottomMessage(message: String) = with(binding) {
        tvMessageErrorBottom.text = message
    }

    fun onClickListenerBottom(onClickReload: () -> Unit) = with(binding) {
        tvReloadErrorBottom.setOnClickListener { onClickReload() }
    }

    fun setBottomTitle(title: String) = with(binding) {
        tvTitleErrorBottom.text = title
    }

    fun setVisibleImage(isVisible: Boolean) = with(binding) {
        if (isVisible) ivError.show() else ivError.hide()
    }

    fun onClickListener(onClickReload: () -> Unit) = with(binding) {
        tvReloadError.setOnClickListener { onClickReload() }
    }

    fun hideReloadError() = with(binding) {
        tvReloadError.hide()
    }

    fun hideBottomReloadError() = with(binding) {
        tvReloadErrorBottom.hide()
    }

    fun hideBottomTitle() = with(binding) {
        tvTitleErrorBottom.invisible()
    }

    fun setSizeImage(width: Float, height: Float) = with(binding) {
        if (width != ONE_HUNDRED_FIFTY) ivError.layoutParams.width = width.toInt()
        if (height != ONE_HUNDRED_FIFTY) ivError.layoutParams.height = height.toInt()
    }

    fun setRefreshButtonVisibility(isVisible: Boolean) = with(binding) {
        tvReloadError.isVisible = isVisible
    }
}
