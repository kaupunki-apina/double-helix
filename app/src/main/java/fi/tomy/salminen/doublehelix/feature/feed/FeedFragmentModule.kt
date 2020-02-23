package fi.tomy.salminen.doublehelix.feature.feed

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragment
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository


@Module
class FeedFragmentModule : BaseFragmentModule<FeedFragment>() {

    @Provides
    fun provideFeedFragmentViewModelFactory(
        fragment: BaseFragment,
        articleRepository: ArticleRepository,
        subscriptionRepository: SubscriptionRepository,
        app: DoubleHelixApplication,
        vmFactory: ArticleListItemViewModel.Factory
    ): FeedFragmentViewModel.Factory {
        return FeedFragmentViewModel.Factory(
            articleRepository,
            subscriptionRepository,
            app,
            vmFactory,
            fragment.arguments?.getParcelable(FeedFragment.EXTRA_FEED_URI)
        )
    }

    @Provides
    fun provideArticleListItemViewModelFactory(dateFormatter: DateFormatter): ArticleListItemViewModel.Factory {
        return ArticleListItemViewModel.Factory(dateFormatter)
    }

    @Provides
    fun provideFeedViewModel(fragment: BaseFragment, factory: FeedFragmentViewModel.Factory): FeedFragmentViewModel {
        return ViewModelProviders.of(fragment, factory)[FeedFragmentViewModel::class.java]
    }
}