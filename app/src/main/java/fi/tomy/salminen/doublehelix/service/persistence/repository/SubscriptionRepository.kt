package fi.tomy.salminen.doublehelix.service.persistence.repository

import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SubscriptionRepository @Inject constructor(database: DoubleHelixDatabase) {
    private val subscriptionDao = database.subscriptionDao()
    val subscription = subscriptionDao.getAll()


    fun insertSubscription(vararg subscriptionEntity: SubscriptionEntity): Observable<Unit> {
        return Observable.fromCallable { subscriptionDao.insertAll(*subscriptionEntity) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}