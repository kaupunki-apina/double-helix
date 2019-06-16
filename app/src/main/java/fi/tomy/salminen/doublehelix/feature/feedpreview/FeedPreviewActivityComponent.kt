package fi.tomy.salminen.doublehelix.feature.feedpreview

import dagger.Subcomponent
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityComponent
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule


@ActivityScope
@Subcomponent(modules = [FeedPreviewActivityModule::class])
interface FeedPreviewActivityComponent : BaseActivityComponent {
    fun inject(feedPreviewActivity: FeedPreviewActivity)

    fun plus(
        feedPreviewFragmentModule: FeedPreviewFragmentModule,
        baseFragmentModule: BaseFragmentModule
    ): FeedPreviewFragmentComponent
}