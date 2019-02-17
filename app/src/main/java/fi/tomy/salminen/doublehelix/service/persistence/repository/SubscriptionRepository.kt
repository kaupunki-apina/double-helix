package fi.tomy.salminen.doublehelix.service.persistence.repository

import android.arch.lifecycle.LiveData
import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SubscriptionRepository @Inject constructor(database: DoubleHelixDatabase) {
    private val feedDao = database.feedDao()
    val feedEntity: LiveData<List<FeedEntity>> = feedDao.getAll()
}