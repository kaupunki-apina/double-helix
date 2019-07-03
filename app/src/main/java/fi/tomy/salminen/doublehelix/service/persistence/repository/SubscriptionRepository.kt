package fi.tomy.salminen.doublehelix.service.persistence.repository


import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SubscriptionRepository @Inject constructor(database: DoubleHelixDatabase) {
    private val subscriptionDao = database.subscriptionDao()

    val subscription = subscriptionDao.getAll()


    fun insertSubscription(vararg subscriptionEntity: SubscriptionEntity): Completable {
        return Completable.fromCallable { subscriptionDao.insertAll(*subscriptionEntity) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSubscriptionsMaybe(): Maybe<List<SubscriptionEntity>> {
        return subscriptionDao.getAllMaybe()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSubscriptionByUrl(url: String): Maybe<SubscriptionEntity> {
        return subscriptionDao.getByUrlMaybe(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}