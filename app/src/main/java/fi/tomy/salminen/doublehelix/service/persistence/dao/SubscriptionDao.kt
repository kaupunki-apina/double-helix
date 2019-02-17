package fi.tomy.salminen.doublehelix.service.persistence.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity


@Dao
interface SubscriptionDao {
    @Query("SELECT * FROM subscription_table")
    fun getAll(): LiveData<List<SubscriptionEntity>>

    @Insert
    fun insertAll(vararg dataEntities: SubscriptionEntity)
}