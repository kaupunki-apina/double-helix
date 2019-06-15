package fi.tomy.salminen.doublehelix.service.persistence.repository

import androidx.lifecycle.LiveData
import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import fi.tomy.salminen.doublehelix.service.rss.RssService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    database: DoubleHelixDatabase,
    private val rssService: RssService,
    private val articleFactory: ArticleEntity.Factory,
    val subscriptionRepository: SubscriptionRepository
    ) {
    private val articleDao = database.articleDao()

    fun getArticles(): LiveData<List<ArticleDatabaseView>> {
        return articleDao.getAll()
    }

    fun getArticleById(articleId: Int): Maybe<ArticleDatabaseView> {
        return articleDao.getWhereMaybe(articleId).subscribeOn(Schedulers.io())
    }

    fun updateArticles(): Completable {
        return subscriptionRepository.getSubscriptionsMaybe()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toFlowable()
            .flatMap { Flowable.fromIterable(it) }
            .doOnNext{ subscriptionEntity ->
                rssService.getRssFeed(subscriptionEntity.url)
                    .flatMap { rssModel ->
                        Flowable.fromIterable(rssModel.channel?.items)
                            .concatMapMaybe { rssItem ->
                                Maybe.fromSingle(articleFactory.from(rssItem, subscriptionEntity))
                                    // If article cannot be parsed, ignore it
                                    .onErrorResumeNext { _: Throwable -> Maybe.empty<ArticleEntity>() }
                            }
                    }.toList()
                    .doOnSuccess { articleEntities ->
                        if (articleEntities != null) {
                            articleDao.update(subscriptionEntity.id, articleEntities)
                        }
                    }
            }
            .ignoreElements()
    }
}