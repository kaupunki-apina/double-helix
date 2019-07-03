package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.core.BaseFragment
import fi.tomy.salminen.doublehelix.inject.fragment.ForFragment
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository


@Module
class FeedFragmentModule {

    @Provides
    fun provideFeedFragmentViewModelFactory(
        articleRepository: ArticleRepository,
        subscriptionRepository: SubscriptionRepository,
        app: Application
    ): FeedFragmentViewModel.Factory {
        return FeedFragmentViewModel.Factory(articleRepository, subscriptionRepository, app)
    }

    @Provides
    fun provideArticleListItemViewModelFactory(dateFormatter: DateFormatter): ArticleListItemViewModel.Factory {
        return ArticleListItemViewModel.Factory(dateFormatter)
    }

    @Provides
    fun provideFeedViewModel(fragment: BaseFragment<*>, factory: FeedFragmentViewModel.Factory): FeedFragmentViewModel {
        return ViewModelProviders.of(fragment, factory)[FeedFragmentViewModel::class.java]
    }

    @Provides
    fun provideArticleListAdapter(vmFactory: ArticleListItemViewModel.Factory): ArticleListAdapter {
        return ArticleListAdapter(vmFactory)
    }


    @Provides
    fun provideLayoutManager(@ForFragment context: Context): RecyclerView.LayoutManager {
        return LinearLayoutManager(context)
    }
}