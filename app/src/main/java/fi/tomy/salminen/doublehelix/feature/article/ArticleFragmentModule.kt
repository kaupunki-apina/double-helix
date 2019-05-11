package fi.tomy.salminen.doublehelix.feature.article

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository


@Module(includes = [BaseFragmentModule::class])
class ArticleFragmentModule {

    @Provides
    fun provideArticleFragmentViewModelFactory(
        articleRepository: ArticleRepository,
        app: Application
    ): ArticleFragmentViewModel.Factory {
        return ArticleFragmentViewModel.Factory(articleRepository, app)
    }

    @Provides
    fun provideFeedViewModel(fragment: Fragment, factory: ArticleFragmentViewModel.Factory): ArticleFragmentViewModel {
        return ViewModelProviders.of(fragment, factory)[ArticleFragmentViewModel::class.java]
    }
}