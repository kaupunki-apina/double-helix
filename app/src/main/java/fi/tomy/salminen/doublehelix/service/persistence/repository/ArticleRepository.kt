package fi.tomy.salminen.doublehelix.service.persistence.repository

import androidx.lifecycle.LiveData
import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import fi.tomy.salminen.doublehelix.service.rss.RssService
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(
    database: DoubleHelixDatabase,
    val rssService: RssService,
    val articleFactory: ArticleEntity.Factory
) {
    private val articleDao = database.articleDao()
    private val subscriptionDao = database.subscriptionDao()

    fun getArticles(): LiveData<List<ArticleDatabaseView>> {
        return articleDao.getAll()
    }

    fun getArticleById(articleId: Int): Maybe<ArticleDatabaseView> {
        return articleDao.getWhereMaybe(articleId).subscribeOn(Schedulers.io())
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