package fi.tomy.salminen.doublehelix.inject.activity

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.feature.feedpreview.FeedPreviewActivity
import fi.tomy.salminen.doublehelix.feature.feedpreview.FeedPreviewActivityModule
import fi.tomy.salminen.doublehelix.feature.mainfeed.FeedActivity
import fi.tomy.salminen.doublehelix.feature.mainfeed.FeedActivityModule


@Module
abstract class ActivityInjectionModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [FeedActivityModule::class])
    abstract fun feedActivityInjector(): FeedActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FeedPreviewActivityModule::class])
    abstract fun feedPreviewActivityInjector(): FeedPreviewActivity
}