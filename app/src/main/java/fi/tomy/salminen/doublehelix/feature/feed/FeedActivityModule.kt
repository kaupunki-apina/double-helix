package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.common.ChromeCustomTabBinder
import fi.tomy.salminen.doublehelix.core.BaseActivity


@Module
class FeedActivityModule {

    @Provides
    fun provideFeedActivityViewModelFactory(app: Application): FeedActivityViewModel.Factory {
        return FeedActivityViewModel.Factory(app)
    }

    @Provides
    fun provideFeedViewModel(
        activity: BaseActivity<*>,
        factory: FeedActivityViewModel.Factory
    ): FeedActivityViewModel {
        return ViewModelProviders.of(activity, factory)[FeedActivityViewModel::class.java]
    }

    @Provides
    fun provideChromeCustomTabBinder(): ChromeCustomTabBinder {
        return ChromeCustomTabBinder()
    }
}
