package fi.tomy.salminen.doublehelix.feature.feedpreview

import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListItemViewModel
import fi.tomy.salminen.doublehelix.feature.articlelist.ForArticleList
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import io.reactivex.Completable
import io.reactivex.Flowable


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
            vmFactory: ArticleListItemViewModel.Factory
        ): Flowable<List<ArticleListItemViewModel>> {
            return articleRepository.getArticles().map { articles ->
                articles.map { article ->
                    vmFactory.create(article)
                }
            }
        }

        @Provides
        @ForArticleList
        fun requestRefresh(articleRepository: ArticleRepository): Completable {
            return articleRepository.updateArticles()
        }
    }
}