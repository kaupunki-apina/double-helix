package fi.tomy.salminen.doublehelix.feature.feed

import dagger.Component
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplicationComponent
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityComponent


@ActivityScope
@Component(
    dependencies = [
        DoubleHelixApplicationComponent::class,
        BaseActivityComponent::class
    ],
    modules = [
        FeedActivityModule::class
    ]
)
interface FeedActivityComponent {
    fun inject(feedActivity: FeedActivity)

    @Component.Factory
    interface Factory {
        fun create(
            appComponent : DoubleHelixApplicationComponent,
            activityComponent: BaseActivityComponent,
            feedActivityModule : FeedActivityModule
        ): FeedActivityComponent
    }
}