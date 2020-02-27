package fi.tomy.salminen.doublehelix.feature.feedpreview

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragment
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragmentModule
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope


@Module
abstract class FeedPreviewActivityModule {

    companion object{
        @Provides
        fun provideDefaultUrl(activity: FeedPreviewActivity): String? {
            return activity.intent?.data?.toString()
        }
    }

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ArticleListFragmentModule::class,
            FeedPreviewToArticleListModule::class
        ]
    )
    abstract fun feedFragmentInjector(): ArticleListFragment
}