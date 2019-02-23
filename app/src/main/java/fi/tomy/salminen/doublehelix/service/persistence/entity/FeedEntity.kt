package fi.tomy.salminen.doublehelix.service.persistence.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

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