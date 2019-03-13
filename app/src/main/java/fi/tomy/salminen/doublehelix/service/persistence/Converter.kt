package fi.tomy.salminen.doublehelix.service.persistence

import androidx.room.TypeConverter
import java.util.*


class Converter {
    //region Date converter
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun toTimestamp(date: Date?): Long? {
        return date?.time
    }
    //endregion
}
