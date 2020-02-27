package fi.tomy.salminen.doublehelix.feature.mainfeed


import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.common.ChromeCustomTabBinder
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragment
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragmentModule
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListItemViewModel
import fi.tomy.salminen.doublehelix.feature.articlelist.ForArticleList
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import io.reactivex.Completable
import io.reactivex.Flowable


@Module
abstract class FeedActivityModule {

    companion object {
        @Provides
        fun provideChromeCustomTabBinder(): ChromeCustomTabBinder {
            return ChromeCustomTabBinder()
        }

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

    @FragmentScope
    @ContributesAndroidInjector(modules = [ArticleListFragmentModule::class])
    abstract fun feedFragmentInjector(): ArticleListFragment
}
