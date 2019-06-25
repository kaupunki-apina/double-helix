package fi.tomy.salminen.doublehelix.feature.feed

import dagger.Subcomponent
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityComponent


@ActivityScope
@Subcomponent(modules = [FeedPreviewActivityModule::class])
interface FeedPreviewActivityComponent : BaseActivityComponent {
    fun inject(feedPreviewActivity: FeedPreviewActivity)
}