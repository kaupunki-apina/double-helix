package fi.tomy.salminen.doublehelix.service.persistence

import androidx.room.TypeConverter
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


class Converter {
    //region Date converter
    private val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    @TypeConverter
    fun toOffsetDateTime(value: String?): ZonedDateTime? {
        return value?.let {
            return formatter.parse(value, ZonedDateTime::from)
        }
    }

    @TypeConverter
    fun fromOffsetDateTime(date: ZonedDateTime?): String? {
        return date?.format(formatter)
    }
    //endregion
}
