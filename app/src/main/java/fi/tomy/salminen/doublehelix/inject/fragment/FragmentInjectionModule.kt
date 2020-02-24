package fi.tomy.salminen.doublehelix.inject.fragment

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.feature.feed.FeedFragment
import fi.tomy.salminen.doublehelix.feature.feed.FeedFragmentModule

@Module
abstract class FragmentInjectionModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [FeedFragmentModule::class])
    abstract fun feedFragmentInjector(): FeedFragment
}