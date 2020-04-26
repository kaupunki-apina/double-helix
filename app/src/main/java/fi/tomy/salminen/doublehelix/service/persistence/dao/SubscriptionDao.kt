package fi.tomy.salminen.doublehelix.service.persistence.dao

import androidx.room.*
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

    @Query("SELECT url FROM subscription")
    fun getUrls(): Flowable<List<String>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg dataEntities: SubscriptionEntity) : List<Long>

    @Update
    fun update(vararg dataEntities: SubscriptionEntity)

    @Query("UPDATE subscription SET description = :description WHERE url = :url")
    fun updateDescription(description: String, url: String)

    @Query("DELETE FROM subscription WHERE url = :url")
    fun deleteWhere(url: String)
}