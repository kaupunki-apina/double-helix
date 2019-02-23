package fi.tomy.salminen.doublehelix.feature.feed

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule
import fi.tomy.salminen.doublehelix.service.persistence.repository.FeedRepository


@Module(includes = [BaseActivityModule::class])
class FeedActivityModule {

    @Provides
    fun provideFeedPagerAdapter(activity: AppCompatActivity, feedRepository: FeedRepository): FeedPagerAdapter {
        return FeedPagerAdapter(activity.supportFragmentManager, feedRepository.feed)
    }

    @Provides
    fun provideFeedActivityViewModelFactory(feedRepository: FeedRepository): FeedActivityViewModel.Factory {
        return FeedActivityViewModel.Factory(feedRepository)
    }

    @Provides
    fun provideFeedViewModel(activity: AppCompatActivity, factory: FeedActivityViewModel.Factory): FeedActivityViewModel {
        return ViewModelProviders.of(activity, factory)[FeedActivityViewModel::class.java]
    }
}
