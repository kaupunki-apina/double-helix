package fi.tomy.salminen.doublehelix.feature.mainfeed


import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.common.ChromeCustomTabBinder


@Module
abstract class FeedActivityModule {

    companion object {

        @Provides
        fun provideChromeCustomTabBinder(): ChromeCustomTabBinder {
            return ChromeCustomTabBinder()
        }
    }
}
