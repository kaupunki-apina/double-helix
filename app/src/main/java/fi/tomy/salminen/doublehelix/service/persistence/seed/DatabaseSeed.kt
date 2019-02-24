package fi.tomy.salminen.doublehelix.service.persistence.seed

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.inject.Injector
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import javax.inject.Inject


class DatabaseSeed(val application: android.app.Application) : RoomDatabase.Callback(), Injector<SeedComponent> {
    @Inject lateinit var subscriptionRepository: SubscriptionRepository

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
        subscriptionRepository.insertSubscription(*SubscriptionEntity.seed()).subscribe()
    }
}