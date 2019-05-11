package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import fi.tomy.salminen.doublehelix.inject.fragment.ForFragment
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository

@Module(includes = [BaseFragmentModule::class])
class FeedFragmentModule {
    @Provides
    fun provideFeedFragmentViewModelFactory(
        articleRepository: ArticleRepository,
        app: Application
    ): FeedFragmentViewModel.Factory {
        return FeedFragmentViewModel.Factory(articleRepository, app)
    }

    @Provides
    fun provideArticleListItemViewModelFactory(dateFormatter: DateFormatter): ArticleListItemViewModel.Factory {
        return ArticleListItemViewModel.Factory(dateFormatter)
    }

    @Provides
    fun provideFeedViewModel(fragment: Fragment, factory: FeedFragmentViewModel.Factory): FeedFragmentViewModel {
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