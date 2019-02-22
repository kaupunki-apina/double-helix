package fi.tomy.salminen.doublehelix.service.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import fi.tomy.salminen.doublehelix.service.persistence.dao.ArticleDao
import fi.tomy.salminen.doublehelix.service.persistence.dao.FeedDao
import fi.tomy.salminen.doublehelix.service.persistence.dao.SubscriptionDao
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity

@Database(entities = [
    FeedEntity::class,
    SubscriptionEntity::class,
    ArticleEntity::class
], version = 1)
abstract class DoubleHelixDatabase : RoomDatabase() {
    abstract fun feedDao(): FeedDao
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun articleDao(): ArticleDao
}