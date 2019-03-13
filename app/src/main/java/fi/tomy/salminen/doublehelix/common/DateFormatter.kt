package fi.tomy.salminen.doublehelix.common


import android.content.Context
import android.text.format.DateFormat
import fi.tomy.salminen.doublehelix.inject.application.ForApplication
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class DateFormatter @Inject constructor (@ForApplication context: Context) {
    // RFC 2822 date pattern
    val pattern = "EEE, dd MMM yyyy HH:mm Z"
    val parser = SimpleDateFormat(pattern, Locale.US)
    val formatter = DateFormat.getDateFormat(context)

    fun parse(dateString: String?) : Date? {
        return if (dateString != null) parser.parse(dateString) else null
    }

    fun format(date: Date) : String {
        return formatter.format(date)
    }
}