package fi.tomy.salminen.doublehelix.feature.feed

import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule


@Module
class FeedFragmentModule : BaseFragmentModule<FeedFragment>() {

    @Provides
    fun provideArticleListItemViewModelFactory(dateFormatter: DateFormatter): ArticleListItemViewModel.Factory {
        return ArticleListItemViewModel.Factory(dateFormatter)
    }
}