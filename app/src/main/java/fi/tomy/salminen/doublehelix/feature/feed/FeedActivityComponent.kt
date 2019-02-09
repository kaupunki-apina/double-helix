package fi.tomy.salminen.doublehelix.feature.feed

import dagger.Subcomponent
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityComponent


@ActivityScope
@Subcomponent(modules = [FeedActivityModule::class])
interface FeedActivityComponent : BaseActivityComponent {
    fun inject(feedActivity: FeedActivity)
}