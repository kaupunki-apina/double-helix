package fi.tomy.salminen.doublehelix.inject.viewmodel

import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import androidx.lifecycle.ViewModel
import dagger.Module
import dagger.multibindings.IntoMap
import fi.tomy.salminen.doublehelix.feature.feed.FeedActivityViewModel
import fi.tomy.salminen.doublehelix.feature.feed.FeedFragmentViewModel
import fi.tomy.salminen.doublehelix.feature.feed.FeedPreviewActivityViewModel
import fi.tomy.salminen.doublehelix.feature.viewmodel.ViewModelFactory


@Module
abstract class ViewModelInjectModule {

    @Binds
    @IntoMap
    @ViewModelKey(FeedFragmentViewModel::class)
    internal abstract fun bindFeedFragmentViewModel(feedFragmentViewModel: FeedFragmentViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedActivityViewModel::class)
    internal abstract fun bindFeedActivityViewModel(feedActivityViewModel: FeedActivityViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FeedPreviewActivityViewModel::class)
    internal abstract fun bindFeedPreviewActivityViewModel(feedPreviewActivityViewModel: FeedPreviewActivityViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}