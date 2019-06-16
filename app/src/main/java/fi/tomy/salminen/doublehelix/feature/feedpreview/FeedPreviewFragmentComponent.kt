package fi.tomy.salminen.doublehelix.feature.feedpreview

import dagger.Subcomponent
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentComponent
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope

@FragmentScope
@Subcomponent(modules = [FeedPreviewFragmentModule::class])
interface FeedPreviewFragmentComponent : BaseFragmentComponent {

    fun inject(feedPreviewFragment: FeedPreviewFragment)
}