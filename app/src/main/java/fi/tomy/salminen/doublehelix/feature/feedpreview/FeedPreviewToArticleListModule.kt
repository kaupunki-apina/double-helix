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
            @ActivityScope isValidUrl: BehaviorSubject<Boolean>,
            @ActivityScope urlSubject: BehaviorSubject<String>
        ): Flowable<List<ArticleListItemViewModel>> {
            return urlSubject
                .distinctUntilChanged()
                .doOnEach { isValidUrl.onNext(false) }
                .flatMap {
                    articleRepository.getArticlesByUrl(it)
                        .doOnComplete{ isValidUrl.onNext(true) }
                        .doOnError { isValidUrl.onNext(false) }
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