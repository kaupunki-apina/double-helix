package fi.tomy.salminen.doublehelix.service.persistence.repository

import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import fi.tomy.salminen.doublehelix.service.rss.RssService
import io.reactivex.*
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
    private val TAG = "ArticleRepository"
    private val articleDao = database.articleDao()

    fun getArticles(): LiveData<List<ArticleDatabaseView>> {
        return articleDao.getAll()
    }

    fun getArticlesByUrl(uri: Uri): Observable<List<Pair<SubscriptionEntity, ArticleEntity>>> {
        return rssService.getRssFeed(uri.toString())
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError {err ->
                Log.e(
                    TAG,
                    "Failed to fetch feed from $uri, Message:${err.message}"
                )
            }
            .map {
                "Mapping"
                val subEntity = SubscriptionEntity.from(it)
                Pair(subEntity, it)
            }
            .flatMap {
                Observable.fromIterable(it.second.channel?.items)
                    .flatMapMaybe { rssItem ->
                        Maybe.fromSingle(articleFactory.from(rssItem, it.first))
                            // If article cannot be parsed, ignore it
                            .onErrorResumeNext { _: Throwable ->
                                Log.i(
                                    TAG,
                                    "Parsing article failed likely due to unexpected date format - Discarding item"
                                )
                                Maybe.empty<ArticleEntity>()
                            }
                    }
                    .map { article -> Pair(it.first, article) }
                    .toList()
                    .toObservable()
            }
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
            .flatMap {
                rssService.getRssFeed(it.url)
                    .map { rssModel -> Pair(it, rssModel) }
                    .toFlowable(BackpressureStrategy.BUFFER)
                    .doOnError {err ->
                        // TODO Subscription should be removed if the host is not found
                        Log.e(
                            TAG,
                            "Failed to fetch feed from ${it.url}, Message:${err.message}"
                        )
                    }
            }
            .flatMap {
                Flowable.fromIterable(it.second.channel?.items)
                    .flatMapMaybe { rssItem ->
                        Maybe.fromSingle(articleFactory.from(rssItem, it.first))
                            // If article cannot be parsed, ignore it
                            .onErrorResumeNext { _: Throwable ->
                                Log.d(
                                    TAG,
                                    "Parsing article failed likely due to unexpected date format - Discarding item"
                                )
                                Maybe.empty<ArticleEntity>()
                            }
                    }
                    .toList()
                    .toFlowable()
            }
            .doOnNext { it?.let {  articles -> articleDao.update(articles) } }
            .ignoreElements()
    }
}
