package fi.tomy.salminen.doublehelix.service.persistence.repository

import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.ArticleViewModel
import fi.tomy.salminen.doublehelix.service.rss.RssService
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    database: DoubleHelixDatabase,
    val rssService: RssService,
    val articleFactory: ArticleEntity.Factory,
    val dateFormatter: DateFormatter
) {
    private val articleDao = database.articleDao()
    private val subscriptionDao = database.subscriptionDao()

    fun getArticles(): Flowable<List<ArticleViewModel>> {
        return articleDao.getAll()
            .map { entities ->
                entities.map { entity ->
                    ArticleViewModel(entity, dateFormatter)
                }
            }
    }

    fun updateArticles(): Flowable<List<ArticleEntity>> {
        return subscriptionDao.getAllMaybe()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .flatMap { Flowable.fromIterable(it) }
            .flatMap { subscriptionEntity ->
                rssService.getRssFeed(subscriptionEntity.url)
                    .map { rssModel ->
                        rssModel.channel?.items?.map { rssItem ->
                            articleFactory.from(
                                rssItem,
                                subscriptionEntity
                            )
                        }
                    }
                    .doOnEach { articleEntities ->
                        if (articleEntities.value != null) {
                            articleDao.update(subscriptionEntity.id, articleEntities.value!!)
                        }
                    }
            }
    }
}