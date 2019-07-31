package fi.tomy.salminen.doublehelix.feature.feed


import android.os.Bundle
import dagger.BindsInstance
import dagger.Component
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplicationComponent
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentComponent
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope

@FragmentScope
@Component(
    dependencies = [
        DoubleHelixApplicationComponent::class,
        BaseFragmentComponent::class
    ],
    modules = [FeedFragmentModule::class]
)
interface FeedFragmentComponent {

    fun inject(feedFragment: FeedFragment)

    @Component.Factory
    interface Factory {
        fun create(
            doubleHelixApplicationComponent: DoubleHelixApplicationComponent,
            baseFragmentComponent: BaseFragmentComponent,
            feedFragmentModule: FeedFragmentModule,
            @BindsInstance arguments: Bundle?
        ) : FeedFragmentComponent
    }
}