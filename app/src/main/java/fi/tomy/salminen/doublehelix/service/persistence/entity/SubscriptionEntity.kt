package fi.tomy.salminen.doublehelix.service.persistence.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.PrimaryKey
import fi.tomy.salminen.doublehelix.service.rss.RssModel


@Entity(tableName = "subscription")
data class SubscriptionEntity(
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
            return arrayOf(
                SubscriptionEntity("http://www.nasa.gov/rss/dyn/educationnews.rss", "NASA Education"),
                SubscriptionEntity("https://www.nasa.gov/rss/dyn/breaking_news.rss", "NASA Breaking news"),
                SubscriptionEntity("https://www.nasa.gov/rss/dyn/lg_image_of_the_day.rss", "NASA Image of the day")
            )
        }

        fun from(rssModel: RssModel) : SubscriptionEntity {
            return SubscriptionEntity("", rssModel.channel?.title)
        }

        fun from(url: String) : SubscriptionEntity {
            return SubscriptionEntity(url, "")
        }
    }
}