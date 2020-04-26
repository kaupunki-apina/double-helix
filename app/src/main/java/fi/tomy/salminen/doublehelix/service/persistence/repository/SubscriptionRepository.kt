package fi.tomy.salminen.doublehelix.service.persistence.repository


import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import io.reactivex.Completable
import io.reactivex.Flowable
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
        return Completable.fromCallable { subscriptionDao.insert(*subscriptionEntity) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun updateDescription(subscriptionEntity: SubscriptionEntity): Completable {
        return Completable.fromCallable { subscriptionDao.updateDescription(subscriptionEntity.description ?: "", subscriptionEntity.url) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun deleteSubscriptionByUrl(url: String) : Completable {
        return Completable.fromCallable { subscriptionDao.deleteWhere(url) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSubscriptionsMaybe(): Maybe<List<SubscriptionEntity>> {
        return subscriptionDao.getAllMaybe()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getUrls(): Flowable<List<String>> {
        return subscriptionDao.getUrls()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSubscriptionByUrlMaybe(url: String): Maybe<SubscriptionEntity> {
        return subscriptionDao.getByUrlMaybe(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    fun getSubsctiptionByUrl(url: String): Flowable<List<SubscriptionEntity>> {
        return subscriptionDao.getByUrl(url)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}