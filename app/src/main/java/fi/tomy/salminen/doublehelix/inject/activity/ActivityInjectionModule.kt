package fi.tomy.salminen.doublehelix.inject.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.feature.feed.FeedActivity
import fi.tomy.salminen.doublehelix.feature.feed.FeedActivityModule
import fi.tomy.salminen.doublehelix.feature.feed.FeedPreviewActivity
import fi.tomy.salminen.doublehelix.feature.feed.FeedPreviewActivityModule
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentInjectionModule


@Module(includes = [FragmentInjectionModule::class])
abstract class ActivityInjectionModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [FeedActivityModule::class])
    abstract fun feedActivityInjector(): FeedActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FeedPreviewActivityModule::class])
    abstract fun feedPreviewActivityInjector(): FeedPreviewActivity
}