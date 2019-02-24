package fi.tomy.salminen.doublehelix.service.persistence.entity

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import fi.tomy.salminen.doublehelix.service.rss.RssModel

@Entity(tableName = "article", foreignKeys = [
    ForeignKey(
        entity = SubscriptionEntity::class,
        parentColumns = ["id"],
        childColumns = ["subscription_id"]
    )
])
class ArticleEntity(
    @field:Nullable
    var title: String?,

    @field:Nullable
    var description: String?,

    @field:Nullable
    var link: String?,

    @field:Nullable
    var publishDate: String?,

    @field:Nullable
    var imageUrl: String?,

    @field:NonNull
    @field:ColumnInfo(name = "subscription_id")
    var subscriptionId: Int
) {
    @field:PrimaryKey(autoGenerate = true)
    @field:NonNull
    var id: Int = 0

    companion object {
        fun from(rssItem: RssModel.RssChannel.RssItem, subscriptionEntity: SubscriptionEntity): ArticleEntity {
            return ArticleEntity(
                rssItem.title,
                rssItem.description,
                rssItem.link,
                rssItem.publishDate,
                rssItem.image,
                subscriptionEntity.id
            )
        }
    }
}
