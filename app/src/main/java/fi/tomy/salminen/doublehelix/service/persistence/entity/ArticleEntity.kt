package fi.tomy.salminen.doublehelix.service.persistence.entity

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "article", foreignKeys = [
    ForeignKey(
        entity = SubscriptionEntity::class,
        parentColumns = ["id"],
        childColumns = ["subscription_id"]
    )
])
class ArticleEntity(
    @field:NonNull
    var title: String,

    @field:NonNull
    var description: String,

    @field:NonNull
    var link: String,

    @field:NonNull
    var publishDate: String,

    @field:NonNull
    var imageUrl: String,

    @field:NonNull
    @field:ColumnInfo(name = "subscription_id")
    var feedId: Int
) {
    @field:PrimaryKey(autoGenerate = true)
    @field:NonNull
    var id: Int = 1

    companion object {
        fun seed(): Array<FeedEntity> {
            return arrayOf(FeedEntity("NASA news"))
        }
    }
}
