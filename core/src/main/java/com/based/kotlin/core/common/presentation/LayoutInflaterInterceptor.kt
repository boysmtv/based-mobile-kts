package com.based.kotlin.core.common.presentation

import android.view.Menu
import android.widget.EditText
import android.widget.Toolbar
import androidx.core.view.forEach
import com.based.kotlin.core.data.Translator
import com.google.android.material.button.MaterialButton
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import io.github.inflationx.viewpump.InflateResult
import io.github.inflationx.viewpump.Interceptor

class LayoutInflaterInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): InflateResult {
        val result = chain.proceed(chain.request())
        val view = result.view ?: return result

        when (view) {
            is MaterialTextView -> view.transformMaterialTextView()
            is MaterialButton -> transformMaterialButton(view)
            is TextInputEditText -> transformTextInputEditText(view)
            is MaterialCheckBox -> transformMaterialCheckBox(view)
            is EditText -> transformEditText(view)
            is Toolbar -> transformToolbar(view)
            is Menu -> transformMenu(view)
            else -> Unit
        }

        return result
    }

    private fun transformMenu(view: Menu) {
        view.forEach { item ->
            item.title = Translator.get(item.title.toString())
        }
    }

    private fun transformToolbar(view: Toolbar) {
        view.title = Translator.get(view.title.toString())
    }

    private fun MaterialTextView.transformMaterialTextView() {
        text = Translator.get(text.toString())
    }

    private fun transformMaterialButton(materialButton: MaterialButton) = materialButton.apply {
        text = Translator.get(text.toString())
    }

    private fun transformTextInputEditText(textInputEditText: TextInputEditText) =
        textInputEditText.apply {
            hint = Translator.get(hint.toString())
            setText(Translator.get(text.toString()))
        }

    private fun transformEditText(editText: EditText) = editText.apply {
        hint = Translator.get(hint?.toString().orEmpty())
        setText(Translator.get(text.toString()))
    }

    private fun transformMaterialCheckBox(materialCheckBox: MaterialCheckBox) =
        materialCheckBox.apply {
            text = Translator.get(text.toString())
        }
}