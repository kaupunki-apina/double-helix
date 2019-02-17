package fi.tomy.salminen.doublehelix.service.persistence.repository

import android.arch.lifecycle.LiveData
import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FeedRepository @Inject constructor(database: DoubleHelixDatabase) {
    private val feedDao = database.feedDao()
    lateinit var feed: LiveData<List<FeedEntity>>

    fun getFeed(): Observable<LiveData<List<FeedEntity>>> {
        return Observable.fromCallable { feedDao.getAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnNext { feed = it }
    }

    fun insertFeed(vararg feedEntity: FeedEntity): Observable<Unit> {
        return Observable.fromCallable { feedDao.insertAll(*feedEntity) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}