package fi.tomy.salminen.doublehelix.feature.feed

import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule
import fi.tomy.salminen.doublehelix.service.persistence.repository.FeedRepository

@Module(includes = [BaseActivityModule::class])
class FeedActivityModule {

    @Provides
    fun provideFeedActivityViewModelFactory(feedRepository: FeedRepository): FeedActivityViewModel.Factory {
        return FeedActivityViewModel.Factory(feedRepository)
    }
}
