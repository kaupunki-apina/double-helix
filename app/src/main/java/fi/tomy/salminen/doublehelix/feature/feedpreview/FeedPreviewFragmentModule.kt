package fi.tomy.salminen.doublehelix.feature.feedpreview

import android.app.Application
import android.content.Context
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.feature.feed.ArticleListAdapter
import fi.tomy.salminen.doublehelix.feature.feed.ArticleListItemViewModel
import fi.tomy.salminen.doublehelix.feature.feed.FeedFragmentViewModel
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import fi.tomy.salminen.doublehelix.inject.fragment.ForFragment
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository


@Module(includes = [BaseFragmentModule::class])
class FeedPreviewFragmentModule(private val feedUri: Uri?) {

    @Provides
    fun provideFeedFragmentViewModelFactory(
        articleRepository: ArticleRepository,
        subscriptionRepository: SubscriptionRepository,
        app: Application
    ): FeedPreviewFragmentViewModel.Factory {
        return FeedPreviewFragmentViewModel.Factory(articleRepository, subscriptionRepository, feedUri, app)
    }

    @Provides
    fun provideFeedViewModel(
        fragment: Fragment,
        factory: FeedPreviewFragmentViewModel.Factory
    ): FeedPreviewFragmentViewModel {
        return ViewModelProviders.of(fragment, factory)[FeedPreviewFragmentViewModel::class.java]
    }

    @Provides
    fun provideArticleListItemViewModelFactory(dateFormatter: DateFormatter): ArticleListItemViewModel.Factory {
        return ArticleListItemViewModel.Factory(dateFormatter)
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