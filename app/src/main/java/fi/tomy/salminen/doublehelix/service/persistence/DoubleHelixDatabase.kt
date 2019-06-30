package fi.tomy.salminen.doublehelix.service.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fi.tomy.salminen.doublehelix.service.persistence.dao.ArticleDao
import fi.tomy.salminen.doublehelix.service.persistence.dao.SubscriptionDao
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity

@Database(
    entities = [
        SubscriptionEntity::class,
        ArticleEntity::class
    ],
    views = [
        ArticleDatabaseView::class
    ],
    version = 1
)
@TypeConverters(Converter::class)
abstract class DoubleHelixDatabase : RoomDatabase() {
    abstract fun subscriptionDao(): SubscriptionDao
    abstract fun articleDao(): ArticleDao

    companion object {
        const val DB_NAME = "double_helix_database"
    }
}
