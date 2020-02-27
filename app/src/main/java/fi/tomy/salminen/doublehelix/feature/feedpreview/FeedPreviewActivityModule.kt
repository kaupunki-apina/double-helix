package fi.tomy.salminen.doublehelix.feature.feedpreview

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragment
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragmentModule
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope


@Module
abstract class FeedPreviewActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ArticleListFragmentModule::class,
            FeedPreviewToArticleListModule::class
        ]
    )
    abstract fun feedFragmentInjector(): ArticleListFragment
}