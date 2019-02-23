package fi.tomy.salminen.doublehelix.service.persistence.repository

import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.ArticleViewModel
import fi.tomy.salminen.doublehelix.service.rss.RssService
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    val database: DoubleHelixDatabase,
    val rssService: RssService
) {
    private val articleDao = database.articleDao()
    private val subscriptionDao = database.subscriptionDao()

    fun getWhere(feedId: Int): Flowable<List<ArticleViewModel>> {
        return articleDao.getWhere(feedId)
            .map { entities ->
                entities.map { entity ->
                    ArticleViewModel(entity)
                }
            }
    }

    fun updateArticles(feedId: Int) {
        subscriptionDao.getWhere(feedId)
            .flatMap { Flowable.fromIterable(it) }
            .flatMap { subscriptionEntity ->
                rssService.getRssFeed(subscriptionEntity.url)
                    .map { rssModel ->
                        rssModel.channel?.items?.map { rssItem ->
                            ArticleEntity.from(
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
            }.subscribe()
    }
}