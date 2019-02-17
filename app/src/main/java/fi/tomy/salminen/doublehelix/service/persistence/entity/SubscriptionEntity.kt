package fi.tomy.salminen.doublehelix.service.persistence.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import android.support.annotation.Nullable


@Entity(tableName = "subscription_table", foreignKeys = [
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
    var id: Int = 1

    companion object {
        fun seed(): Array<SubscriptionEntity> {
            return arrayOf(SubscriptionEntity(1, "http://www.nasa.gov/rss/dyn/educationnews.rss", null))
        }
    }
}