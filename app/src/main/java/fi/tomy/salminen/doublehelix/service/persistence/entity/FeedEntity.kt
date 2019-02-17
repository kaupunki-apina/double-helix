package fi.tomy.salminen.doublehelix.service.persistence.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "feed_table")
class FeedEntity(
    @field:PrimaryKey(autoGenerate = true)
    @field:NonNull
    val id: Int,

    @field:NonNull
    val name: String
) {
    companion object {
        fun seed(): Array<FeedEntity> {
            return arrayOf(FeedEntity(1, "NASA news"))
        }
    }
}