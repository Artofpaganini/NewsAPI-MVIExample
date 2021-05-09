package by.andersen.dobrov.newsapi.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

// TODO(https://jira.alfa-bank.kz/browse/SE777-250)
class DateFormatter {

    private val dateFormatter = SimpleDateFormat(
        DateFormat.UI_DATE_FORMAT.pattern,
        Locale.getDefault()
    )

    @JvmOverloads
    fun reformat(
        originalFormattedDate: String,
        originalFormat: DateFormat,
        originalTimeZone: String = ALMATY_TIME_ZONE,
        newFormat: DateFormat,
        newTimeZone: String = ALMATY_TIME_ZONE,
    ): String? = reformat(
        originalFormattedDate,
        originalFormat.pattern,
        originalTimeZone,
        newFormat.pattern,
        newTimeZone
    )

    @JvmOverloads
    fun reformat(
        originalFormattedDate: String,
        originalFormat: String,
        originalTimeZone: String = ALMATY_TIME_ZONE,
        newFormat: String,
        newTimeZone: String = ALMATY_TIME_ZONE,
    ): String? {
        val parsedDate = parse(originalFormattedDate, originalFormat, originalTimeZone)

        return format(parsedDate, newFormat, newTimeZone)
    }

    @JvmOverloads
    fun format(
        timestamp: Long,
        format: DateFormat,
        timeZone: String? = null,
    ): String? = format(Date(timestamp), format.pattern, timeZone)

    @JvmOverloads
    fun format(
        date: Date?,
        format: DateFormat,
        timeZone: String? = null,
    ): String? = format(date, format.pattern, timeZone)

    @JvmOverloads
    fun format(
        date: Date?,
        format: String,
        timeZone: String? = null,
    ): String? {
        date
            ?: return null

        return try {
            applyFormat(format)

            if (timeZone == null) dateFormatter.format(date)
            else cloneWithTimeZone(timeZone).format(date)

        } catch (ex: Exception) {
            null
        }?.formatWithColonSeparator()
    }

    @JvmOverloads
    fun parse(
        date: String?,
        format: DateFormat,
        timeZone: String? = null,
    ): Date? = parse(date, format.pattern, timeZone)

    @JvmOverloads
    fun parse(
        date: String?,
        format: String,
        timeZone: String? = null,
    ): Date? {
        val givenDate = date.takeUnless { it.isNullOrBlank() }
            ?: return null

        return try {
            applyFormat(format)

            if (timeZone == null) dateFormatter.parse(givenDate)
            else cloneWithTimeZone(timeZone).parse(givenDate)

        } catch (ex: Exception) {

            null
        }
    }

    @JvmOverloads
    fun getCurrentTime(
        format: String = DateFormat.ISO_8601_DATE_FORMAT.pattern,
        timeZone: String = ALMATY_TIME_ZONE,
    ): String = format(
        date = Date(System.currentTimeMillis()),
        format = format,
        timeZone = timeZone
    )!!

    /**
     * Parses an RFC3339 date/time value.
     *
     * Also, in accordance with the RFC3339 standard, any number of
     * milliseconds digits allowed.
     *
     * <p>
     * If the time zone is specified, this value is normalized to UTC, so to format this date/time
     * value, the time zone shift has to be applied.
     * </p>
     *
     * @param str Date/time string in RFC3339 format
     * @return java.util.Date
     * if parse fails returns null
     */
    fun parseRfc3339OrNull(str: String?): Date? = try {
        val millis = DateTime.parseRfc3339(str).value
        Date(millis)
    } catch (numberFormatException: NumberFormatException) {
        null
    }

    // TODO(https://jira.alfa-bank.kz/browse/SE777-250)
    @JvmOverloads
    fun createDateFormatter(
        format: String,
        userTimeZone: String? = null,
        locale: Locale = Locale.getDefault(),
    ): SimpleDateFormat {
        val formatter = SimpleDateFormat(format, locale)
        userTimeZone?.let {
            formatter.timeZone = TimeZone.getTimeZone(it)
        }

        return formatter
    }

    private fun applyFormat(
        format: String,
    ) = dateFormatter.apply {
        applyPattern(format)
    }

    private fun cloneWithTimeZone(
        timeZone: String,
    ) = (dateFormatter.clone() as SimpleDateFormat).apply {
        this.timeZone = TimeZone.getTimeZone(timeZone)
    }

    //TODO AX1-1288 добавить coreLibraryDesugaring и переехать на java.time package
    // чтобы не править вручную формат даты в ISO
    private fun String.formatWithColonSeparator(): String {
        val (beforeTimeZone, delimiter, timeZone) = partitionTimeZoneOrNull()
            ?: return this

        val reformattedTimeZone = reformatTimeZone(timeZone)

        return "$beforeTimeZone$delimiter$reformattedTimeZone"
    }

    private fun String.partitionTimeZoneOrNull(): Triple<String, String, String>? {
        val (delimiter, timeZone) = extractTimeZoneOrNull()
            ?: return null

        val beforeTimeZone = substringBeforeLast(delimiter)

        return Triple(beforeTimeZone, delimiter, timeZone)
    }

    private fun String.extractTimeZoneOrNull(): Pair<String, String>? {
        val delimiterPlus = '+'
        val delimiterMinus = '-'

        substringFromEnd(delimiterPlus)
            ?.let {
                return Pair(delimiterPlus.toString(), it)
            }

        substringFromEnd(delimiterMinus)
            ?.let {
                return Pair(delimiterMinus.toString(), it)
            }

        return null
    }

    private fun String.substringFromEnd(
        delimiter: Char,
    ): String? = takeLastWhile { it != delimiter }
        .takeUnless { it.isEmpty() || it.length == length }

    private fun reformatTimeZone(timeZone: String): String {
        val hoursMinutesDelimiter = ':'
        if (timeZone.contains(hoursMinutesDelimiter)) return timeZone

        val hoursMinutesLength = 4
        if (timeZone.length < hoursMinutesLength) return timeZone

        val (hours, minutes) = timeZone.chunked(hoursMinutesLength / 2)

        return "$hours$hoursMinutesDelimiter$minutes"
    }

    companion object {
        const val ALMATY_TIME_ZONE = "GMT+06:00"
    }
}