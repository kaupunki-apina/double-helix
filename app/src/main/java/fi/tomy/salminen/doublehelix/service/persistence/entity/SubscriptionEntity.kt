package fi.tomy.salminen.doublehelix.service.persistence.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "subscription")
class SubscriptionEntity(
    @field:NonNull
    var url: String,

    @field:Nullable
    var description: String?
) {
    @field:PrimaryKey(autoGenerate = true)
    @field:NonNull
    var id: Int = 0

    companion object {
        fun seed(): Array<SubscriptionEntity> {
            return arrayOf(SubscriptionEntity("http://www.nasa.gov/rss/dyn/educationnews.rss", null))
        }
    }
}