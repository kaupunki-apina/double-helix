package fi.tomy.salminen.doublehelix.service.persistence.seed

import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Lazy
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseSeed @Inject constructor(
    // Lazy inject to resolve circular dependency
    // More info here https://stackoverflow.com/questions/44709685/how-to-resolve-a-circular-dependency-while-still-using-dagger2
    val subscriptionRepository: Lazy<SubscriptionRepository>
) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        subscriptionRepository.get().insertSubscription(*SubscriptionEntity.seed()).subscribe()
    }
}