package com.based.kotlin.core.common.util

import com.based.kotlin.core.data.Translator

object TranslatorUtil {
    fun translateWithValue(localizationKeyId: String, valueToReplace: String) =
        Translator.get(localizationKeyId).replace(Regex(REPLACE_REGEX), valueToReplace)

    fun translateWithValues(localizationKeyId: String, valueToReplace: List<Pair<String, String>>): String {
        var translatedString = Translator.get(localizationKeyId)
        valueToReplace.forEach {
            translatedString = translatedString.replace("__${it.first}__", it.second)
        }
        return translatedString
    }

    private const val REPLACE_REGEX = "(__.+__)"
}