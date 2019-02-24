package fi.tomy.salminen.doublehelix.service.persistence.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.annotation.NonNull
import androidx.annotation.Nullable


@Entity(tableName = "subscription", foreignKeys = [
    ForeignKey(
        entity = FeedEntity::class,
        parentColumns = ["id"],
        childColumns = ["feed_id"]
    )
])
class SubscriptionEntity(
    @field:NonNull
    @field:ColumnInfo(name = "feed_id")
    var feedId: Int,

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
            return arrayOf(SubscriptionEntity(1, "http://www.nasa.gov/rss/dyn/educationnews.rss", null))
        }
    }
}