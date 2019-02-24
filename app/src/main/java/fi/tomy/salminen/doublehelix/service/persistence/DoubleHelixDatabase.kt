package fi.tomy.salminen.doublehelix.service.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import fi.tomy.salminen.doublehelix.service.persistence.dao.ArticleDao
import fi.tomy.salminen.doublehelix.service.persistence.dao.SubscriptionDao
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity

@Database(entities = [
    SubscriptionEntity::class,
    ArticleEntity::class
], version = 1)
abstract class DoubleHelixDatabase : RoomDatabase() {
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun articleDao(): ArticleDao
}