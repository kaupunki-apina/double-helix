package fi.tomy.salminen.doublehelix.service.persistence.seed

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.RoomDatabase
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.inject.Injector
import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.dao.FeedDao
import fi.tomy.salminen.doublehelix.service.persistence.dao.SubscriptionDao
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import fi.tomy.salminen.doublehelix.service.persistence.repository.FeedRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import javax.inject.Inject


class DatabaseSeed(val application: android.app.Application) : RoomDatabase.Callback(), Injector<SeedComponent> {
    @Inject lateinit var subscriptionRepository: SubscriptionRepository
    @Inject lateinit var feedRepository: FeedRepository

    val component by lazy {
        createComponent()
    }

    override fun createComponent(): SeedComponent {
        return (application as DoubleHelixApplication).component.plus(SeedModule())
    }

    override fun inject() {
        component.inject(this)
    }

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        inject()
        feedRepository.insertFeed(*FeedEntity.seed())
            .doOnNext{
                subscriptionRepository.insertSubscription(*SubscriptionEntity.seed()).subscribe()
            }
            .subscribe()
    }
}