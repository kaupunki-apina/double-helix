package fi.tomy.salminen.doublehelix.feature.feed

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.common.ChromeCustomTabBinder
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope


@Module
abstract class FeedActivityModule {

    companion object {
        @Provides
        fun provideFeedActivityViewModelFactory(app: DoubleHelixApplication): FeedActivityViewModel.Factory {
            return FeedActivityViewModel.Factory(app)
        }

        @Provides
        fun provideFeedViewModel(
            activity: FeedActivity,
            factory: FeedActivityViewModel.Factory
        ): FeedActivityViewModel {
            return ViewModelProviders.of(activity, factory)[FeedActivityViewModel::class.java]
        }

        @Provides
        fun provideChromeCustomTabBinder(): ChromeCustomTabBinder {
            return ChromeCustomTabBinder()
        }
    }


}
