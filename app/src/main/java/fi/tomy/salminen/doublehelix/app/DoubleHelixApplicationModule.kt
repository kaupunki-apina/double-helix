package fi.tomy.salminen.doublehelix.app

import dagger.Module
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.feature.feed.FeedActivity
import fi.tomy.salminen.doublehelix.feature.feed.FeedActivityModule
import fi.tomy.salminen.doublehelix.feature.feed.FeedPreviewActivity
import fi.tomy.salminen.doublehelix.feature.feed.FeedPreviewActivityModule
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.inject.application.BaseApplicationModule


@Module(includes = [BaseApplicationModule::class])
abstract class DoubleHelixApplicationModule