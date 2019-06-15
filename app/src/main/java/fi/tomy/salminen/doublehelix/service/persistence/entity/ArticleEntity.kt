package fi.tomy.salminen.doublehelix.service.persistence.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.service.rss.RssModel
import io.reactivex.Single
import java.time.ZonedDateTime
import javax.inject.Inject
import javax.inject.Singleton


@Entity(
    tableName = "article", foreignKeys = [
        ForeignKey(
            entity = SubscriptionEntity::class,
            parentColumns = ["id"],
            childColumns = ["subscriptionId"]
        )
    ]
)
data class ArticleEntity(
    @field:Nullable
    var title: String?,

    @field:Nullable
    var description: String?,

    @field:Nullable
    var link: String?,

    @field:Nullable
    var publishDate: ZonedDateTime?,

    @field:Nullable
    var imageUrl: String?,

    @field:NonNull
    var subscriptionId: Int
) {
    @field:PrimaryKey(autoGenerate = true)
    @field:NonNull
    var id: Int = 0

    @Singleton
    class Factory @Inject constructor(val dateFormatter: DateFormatter) {
        fun from(rssItem: RssModel.RssChannel.RssItem, subscriptionEntity: SubscriptionEntity): Single<ArticleEntity> {
            return dateFormatter.parse(rssItem.publishDate)
                .map {
                    ArticleEntity(
                        rssItem.title,
                        rssItem.description,
                        rssItem.link,
                        it,
                        rssItem.enclosure?.url,
                        subscriptionEntity.id
                    )
                }
        }
    }
}
