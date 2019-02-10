package fi.tomy.salminen.doublehelix.feature.feed


import dagger.Subcomponent
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentComponent
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope

@FragmentScope
@Subcomponent(modules = [FeedFragmentModule::class])
interface FeedFragmentComponent : BaseFragmentComponent {

    fun inject(feedFragment: FeedFragment)
}