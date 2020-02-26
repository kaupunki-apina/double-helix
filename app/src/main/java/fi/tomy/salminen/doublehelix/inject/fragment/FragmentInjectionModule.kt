package fi.tomy.salminen.doublehelix.inject.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragment
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragmentModule

@Module
abstract class FragmentInjectionModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [ArticleListFragmentModule::class])
    abstract fun feedFragmentInjector(): ArticleListFragment
}