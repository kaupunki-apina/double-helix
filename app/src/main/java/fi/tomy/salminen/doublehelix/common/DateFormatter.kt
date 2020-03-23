package fi.tomy.salminen.doublehelix.common

import io.reactivex.Single
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import javax.inject.Inject


class DateFormatter @Inject constructor() {
    // RFC 2822 date pattern
    private val patternA = "EEE, dd MMM yyyy HH:mm[:ss] z"
    // Almost RFC 2822 date pattern, but with timezone offset.
    private val patternB = "EEE, dd MMM yyyy HH:mm[:ss] Z"

    private val parserA = DateTimeFormatter.ofPattern(patternA, Locale.US)
    private val parserB = DateTimeFormatter.ofPattern(patternB, Locale.US)
    private val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

    fun parse(dateString: String?): Single<ZonedDateTime> {
        return Single.fromCallable{
            ZonedDateTime.parse(dateString, parserA)
        }.onErrorResumeNext {
            Single.fromCallable { ZonedDateTime.parse(dateString, parserB)}
        }
    }

    fun format(date: ZonedDateTime): String {
        return date.format(formatter)
    }
}