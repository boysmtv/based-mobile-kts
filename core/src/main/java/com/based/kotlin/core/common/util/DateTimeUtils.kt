package com.based.kotlin.core.common.util

import android.annotation.SuppressLint
import com.based.kotlin.core.common.util.extensions.getLocale
import com.based.kotlin.utilities.constants.Constants.Calender.DATE_TEMPLATE
import com.based.kotlin.utilities.constants.Constants.EMPTY_STRING
import com.based.kotlin.utilities.constants.Constants.TIME_CODE_WIB
import com.based.kotlin.utilities.constants.Constants.TIME_CODE_WITA
import com.based.kotlin.utilities.constants.Constants.TIME_ZONE_WIB
import com.based.kotlin.utilities.constants.Constants.TIME_ZONE_WIT
import com.based.kotlin.utilities.constants.Constants.TIME_ZONE_WITA
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.format.DateTimeParseException
import java.text.SimpleDateFormat
import java.util.Locale

object DateTimeUtils {

    private const val DATE_FORMAT = "dd-MMM-yyyy"
    private const val TIME_FORMAT = "HH:mm:ss"
    private const val SIMPLE_DATE_FORMAT = "dd-MM-yyyy"
    private const val NUMERIC_DATE_FORMAT = "dd-MM-yyyy HH:mm:ss"
    private const val NUMERIC_DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    private const val NUMERIC_DATE_TIME_FORMAT_TIME_ZONE = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    private const val NUMERIC_MONTH_DATE_TIME_FORMAT = "dd-MM-yyyy"
    private const val FULL_DATE_FORMAT = "dd MMMM yyyy"
    private const val DATE_FORMAT_SLASH = "dd/MM/yyyy"
    const val SIMPLE_DATE_TIME_FORMAT = "dd MMM yyyy HH:mm:ss"
    private const val DATE_FORMAT_MONTH = "yyyy-MM-dd"
    private const val DATE_FORMAT_CALENDER = "dd MMM yyyy"

    var dateOnlyPattern: String = DATE_FORMAT
    var timeOnlyPattern: String = TIME_FORMAT
    var simpleDatePattern: String = SIMPLE_DATE_FORMAT
    var datePattern: String = NUMERIC_DATE_FORMAT
    var fullDateFormat = FULL_DATE_FORMAT
    var dateTimePattern: String = NUMERIC_DATE_TIME_FORMAT
    var dateTimePatternUtc = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX"
    var dateTimePatternTimeZone: String = NUMERIC_DATE_TIME_FORMAT_TIME_ZONE
    var dateTimeNumericMonth: String = NUMERIC_MONTH_DATE_TIME_FORMAT
    var dateMonthFormat: String = DATE_FORMAT_MONTH
    var dateCalender: String = DATE_FORMAT_CALENDER
    var dateOnlyPatternSlash: String = DATE_FORMAT_SLASH

    fun convertStringBirthdayToDate(
        date: String?,
        format: String = dateTimePattern
    ): LocalDate? {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        return LocalDate.parse(date, dateFormat)
    }

    fun convertDateToStringBirthday(
        date: LocalDate?,
        format: String = datePattern
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun convertStringToDate(
        date: String?,
        format: String = dateTimePattern
    ): LocalDate? {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        return LocalDate.parse(date, dateFormat)
    }

    fun convertStringToDateFilter(
        date: String?,
        format: String = dateTimePattern
    ): LocalDate? {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale("id"))
        return LocalDate.parse(date, dateFormat)
    }

    fun convertDateToString(
        date: LocalDate?,
        format: String = datePattern
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun convertDateToLocaleId(
        date: LocalDate?,
        format: String = datePattern
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale("id"))
        return dateFormat.format(date)
    }

    fun convertStringToLocalDate(
        date: String?,
        format: String = dateTimePattern
    ): LocalDateTime? {
        var localDateTime: LocalDateTime? = null
        date?.let {
            val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
            localDateTime = LocalDateTime.parse(date, dateFormat)
        }
        return localDateTime
    }

    fun convertStringToLocal(
        date: String?,
        format: String? = dateTimePattern
    ): LocalDateTime? {
        var localDateTime: LocalDateTime? = null
        date?.let {
            val dateFormat: DateTimeFormatter = DateTimeFormatter.ofPattern(format, Locale.getDefault())
            localDateTime = LocalDate.parse(date, dateFormat).atStartOfDay()
        }
        return localDateTime
    }

    fun convertDateToString(
        date: LocalDateTime,
        format: String = datePattern
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        return dateFormat.format(date)
    }

    fun convertDateToStringLocaleId(
        date: LocalDateTime,
        format: String = datePattern
    ): String {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale("id"))
        return dateFormat.format(date)
    }

    fun convertStringDateToStringFormattedDate(
        date: String?,
        inputFormat: String = DATE_TEMPLATE,
        outputFormat: String = datePattern
    ): String = try {
        val dateTimerFormatter = DateTimeFormatter.ofPattern(inputFormat, Locale("id"))
        val localScriptDate: LocalDate = LocalDate.parse(date, dateTimerFormatter)
        convertDateToLocaleId(localScriptDate, outputFormat)
    } catch (e: Exception) {
        EMPTY_STRING
    }

    fun convertStringToLocalDateTimeZone(
        date: String?,
        format: String?,
    ): LocalDateTime? {
        val dateFormat = DateTimeFormatter.ofPattern(format, Locale.getDefault())
        return LocalDateTime.parse(date, dateFormat)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertStringToFullDateFormat(
        date: String,
        format: String = NUMERIC_MONTH_DATE_TIME_FORMAT
    ): String {
        val dateFormat = SimpleDateFormat(format).parse(date)
        return SimpleDateFormat(fullDateFormat).format(dateFormat)
    }

    fun getDateTimeZone(
        date: String
    ): String {
        return when {
            date.contains(TIME_CODE_WIB) -> TIME_ZONE_WIB
            date.contains(TIME_CODE_WITA) -> TIME_ZONE_WITA
            else -> TIME_ZONE_WIT
        }
    }

    private fun <T> doIfDateOrTimeValid(
        defaultValue: String,
        checkedFunction: () -> T,
        doIfValid: (T) -> String
    ): String = try {
        doIfValid(checkedFunction())
    } catch (ex: DateTimeParseException) {
        defaultValue
    }

    fun safeDateTimeParser(
        dateTime: String,
        fullDateFormat: String,
        responDateFormat: String = dateTimePatternUtc
    ) =
        doIfDateOrTimeValid(dateTime, {
            dateTimeParser(dateTime, responDateFormat)
        }) { localDateTimeParserToString(it, fullDateFormat) }

    private fun localDateTimeParserToString(localDateTime: LocalDateTime, dateTimePatter: String): String {
        return localDateTime.format(DateTimeFormatter.ofPattern(dateTimePatter, getLocale()))
    }

    private fun dateTimeParser(rawDate: CharSequence, dateTimePattern: String): LocalDateTime {
        val formatter = DateTimeFormatter.ofPattern(dateTimePattern, getLocale())
        return LocalDateTime.parse(rawDate, formatter)
    }
}