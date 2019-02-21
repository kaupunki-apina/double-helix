package fi.tomy.salminen.doublehelix.service.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import io.reactivex.Flowable


@Dao
interface FeedDao {
    @Query("SELECT * FROM feed_table")
    fun getAll(): Flowable<List<FeedEntity>>

    @Insert
    fun insertAll(vararg dataEntities: FeedEntity)
}