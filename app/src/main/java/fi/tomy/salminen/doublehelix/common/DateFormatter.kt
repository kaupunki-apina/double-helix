package fi.tomy.salminen.doublehelix.common


import android.content.Context
import fi.tomy.salminen.doublehelix.inject.application.ForApplication
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DateFormatter @Inject constructor(@ForApplication context: Context) {
    // RFC 2822 date pattern
    private val pattern = "EEE, dd MMM yyyy HH:mm[:ss] z"
    private val parser = DateTimeFormatter.ofPattern(pattern, Locale.US)
    private val formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)

    fun parse(dateString: String?): ZonedDateTime? {
        return if (dateString != null) ZonedDateTime.parse(dateString, parser) else null
    }

    fun format(date: ZonedDateTime): String {
        return date.format(formatter)
    }
}