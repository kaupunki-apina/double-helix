package fi.tomy.salminen.doublehelix.feature.feed

import android.arch.lifecycle.ViewModelProviders
import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository

@Module(includes = [BaseFragmentModule::class])
class FeedFragmentModule {
    @Provides
    fun provideFeedFragmentViewModelFactory(articleRepository: ArticleRepository): FeedFragmentViewModel.Factory {
        return FeedFragmentViewModel.Factory(articleRepository)
    }

    @Provides
    fun provideFeedViewModel(fragment: Fragment, factory: FeedFragmentViewModel.Factory): FeedFragmentViewModel {
        return ViewModelProviders.of(fragment, factory)[FeedFragmentViewModel::class.java]
    }
}