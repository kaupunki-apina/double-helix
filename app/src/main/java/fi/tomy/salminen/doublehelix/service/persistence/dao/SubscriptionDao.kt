package fi.tomy.salminen.doublehelix.service.persistence.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import io.reactivex.Flowable
import io.reactivex.Maybe


@Dao
interface SubscriptionDao {
    @Query("SELECT * FROM subscription")
    fun getAll(): Flowable<List<SubscriptionEntity>>

    @Query("SELECT * FROM subscription")
    fun getAllMaybe(): Maybe<List<SubscriptionEntity>>

    @Query("SELECT * FROM subscription WHERE url = :url")
    fun getByUrlMaybe(url: String) : Maybe<SubscriptionEntity>

    @Query("SELECT * FROM subscription WHERE url = :url")
    fun getByUrl(url: String) : Flowable<List<SubscriptionEntity>>

    @Insert
    fun insertAll(vararg dataEntities: SubscriptionEntity)

    @Query("DELETE FROM subscription WHERE url = :url")
    fun deleteWhere(url: String)
}