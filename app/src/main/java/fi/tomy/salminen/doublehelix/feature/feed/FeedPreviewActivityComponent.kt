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
        FeedPreviewActivityModule::class
    ]
)
interface FeedPreviewActivityComponent {
    fun inject(feedPreviewActivity: FeedPreviewActivity)

    @Component.Factory
    interface Factory {
        fun create(
            doubleHelixApplicationComponent: DoubleHelixApplicationComponent,
            baseActivityComponent: BaseActivityComponent,
            feedPreviewActivityModule: FeedPreviewActivityModule
        ) : FeedPreviewActivityComponent
    }
}