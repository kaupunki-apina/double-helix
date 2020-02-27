package fi.tomy.salminen.doublehelix.feature.feedpreview

import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListItemViewModel
import fi.tomy.salminen.doublehelix.feature.articlelist.ForArticleList
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


/**
 * Provides dependencies required by ArticleListFragment
 */
@Module
abstract class FeedPreviewToArticleListModule {
    companion object {
        @Provides
        @ForArticleList
        fun provideFeedSource(
            articleRepository: ArticleRepository,
            vmFactory: ArticleListItemViewModel.Factory,
            @ActivityScope urlSubject: BehaviorSubject<String>
        ): Flowable<List<ArticleListItemViewModel>> {
            return urlSubject
                .debounce(500, TimeUnit.MILLISECONDS)
                .flatMap {
                    articleRepository.getArticlesByUrl(it)
                        // TODO Actual error handling
                        .onErrorResumeNext(Observable.just(emptyList()))
                        .map { result ->
                            result.map { pair -> vmFactory.create(pair.first, pair.second) }
                        }
                }.toFlowable(BackpressureStrategy.LATEST)
        }

        @Provides
        @ForArticleList
        fun requestRefresh(): Completable {
            return Completable.complete()
        }
    }
}