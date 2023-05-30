package com.based.kotlin.core.common.util

import android.content.res.Resources
import androidx.annotation.StringRes
import androidx.viewbinding.ViewBinding
import com.based.kotlin.core.base.BaseActivity
import com.based.kotlin.core.data.Translator
import com.based.kotlin.core.ui.extensions.getString
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

inline fun <T> T?.orDefault(
    default: T,
    additionalCheck: (T) -> Boolean = { _ -> true }
): T = if (this != null && additionalCheck(this)) this else default

fun ViewBinding.translate(@StringRes stringId: Int) = Translator.get(root.resources.getString(stringId))

fun ViewBinding.translateWithValue(@StringRes stringId: Int, replacement: String) =
    TranslatorUtil.translateWithValue(root.getString(stringId), replacement)

fun BottomSheetDialogFragment.translate(@StringRes stringId: Int) = Translator.get(getString(stringId))

fun Resources.translate(@StringRes stringId: Int) = Translator.get(getString(stringId))

fun BaseActivity<*>.translate(@StringRes stringId: Int) = Translator.get(getString(stringId))

fun ViewBinding.translate(localization: String) = Translator.get(localization)