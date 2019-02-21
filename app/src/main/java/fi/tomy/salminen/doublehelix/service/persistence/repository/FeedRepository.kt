package fi.tomy.salminen.doublehelix.service.persistence.repository

import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.FeedViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class FeedRepository @Inject constructor(database: DoubleHelixDatabase) {
    private val feedDao = database.feedDao()
    val feed: Flowable<List<FeedViewModel>> = feedDao.getAll()
        .map { entities ->
            entities.map { entity ->
                FeedViewModel(entity)
            }
        }

    fun insertFeed(vararg feedEntity: FeedEntity): Observable<Unit> {
        return Observable.fromCallable { feedDao.insertAll(*feedEntity) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}