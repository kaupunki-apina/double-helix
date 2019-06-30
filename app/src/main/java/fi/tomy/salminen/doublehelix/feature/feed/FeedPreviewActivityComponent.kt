package fi.tomy.salminen.doublehelix.feature.feed

import dagger.Subcomponent
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope


@ActivityScope
@Subcomponent(modules = [FeedPreviewActivityModule::class])
interface FeedPreviewActivityComponent {
    fun inject(feedPreviewActivity: FeedPreviewActivity)
}