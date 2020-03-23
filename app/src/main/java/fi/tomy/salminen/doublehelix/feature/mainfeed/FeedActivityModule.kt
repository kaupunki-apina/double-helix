package fi.tomy.salminen.doublehelix.feature.mainfeed


import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.common.ChromeCustomTabBinder
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragment
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragmentModule
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope


@Module
abstract class FeedActivityModule {

    companion object {
        @Provides
        fun provideChromeCustomTabBinder(): ChromeCustomTabBinder {
            return ChromeCustomTabBinder()
        }
    }

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ArticleListFragmentModule::class,
            MainFeedToArticleListModule::class]
    )
    abstract fun feedFragmentInjector(): ArticleListFragment
}
