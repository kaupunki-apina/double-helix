package fi.tomy.salminen.doublehelix.feature.feed

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule


@Module(includes = [BaseActivityModule::class])
class FeedActivityModule {

    @Provides
    fun provideFeedActivityViewModelFactory(): FeedActivityViewModel.Factory {
        return FeedActivityViewModel.Factory()
    }

    @Provides
    fun provideFeedViewModel(activity: AppCompatActivity, factory: FeedActivityViewModel.Factory): FeedActivityViewModel {
        return ViewModelProviders.of(activity, factory)[FeedActivityViewModel::class.java]
    }
}
