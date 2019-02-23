package fi.tomy.salminen.doublehelix.service.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import io.reactivex.Flowable


@Dao
interface SubscriptionDao {
    @Query("SELECT * FROM subscription")
    fun getAll(): Flowable<List<SubscriptionEntity>>

    @Query( "SELECT * FROM subscription " +
            "WHERE feed_id = :feedId")
    fun getWhere(feedId: Int): Flowable<List<SubscriptionEntity>>

    @Insert
    fun insertAll(vararg dataEntities: SubscriptionEntity)
}