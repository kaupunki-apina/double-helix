package fi.tomy.salminen.doublehelix.service.persistence.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull

@Entity(tableName = "feed")
class FeedEntity(
    @field:NonNull
    var name: String
) {
    @field:PrimaryKey(autoGenerate = true)
    @field:NonNull
    var id: Int = 0

    companion object {
        fun seed(): Array<FeedEntity> {
            return arrayOf(FeedEntity("NASA news"))
        }
    }
}