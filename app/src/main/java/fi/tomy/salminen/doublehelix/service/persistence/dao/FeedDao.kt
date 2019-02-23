package fi.tomy.salminen.doublehelix.service.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import io.reactivex.Flowable


@Dao
interface FeedDao {
    @Query("SELECT * FROM feed")
    fun getAll(): Flowable<List<FeedEntity>>

    @Insert
    fun insertAll(vararg dataEntities: FeedEntity)
}