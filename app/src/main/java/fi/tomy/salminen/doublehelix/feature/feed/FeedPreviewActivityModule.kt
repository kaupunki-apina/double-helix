package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import android.net.Uri
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository


@Module
class FeedPreviewActivityModule(val feedUri: Uri?) {

    @Provides
    @FragmentScope
    fun provideFeedFragmentViewModelFactory(
        articleRepository: ArticleRepository,
        subscriptionRepository: SubscriptionRepository,
        app: Application
    ): FeedPreviewActivityViewModel.Factory {
        return FeedPreviewActivityViewModel.Factory(articleRepository, subscriptionRepository, feedUri, app)
    }

    @Provides
    @FragmentScope
    fun provideFeedViewModel(
        fragment: Fragment,
        factory: FeedPreviewActivityViewModel.Factory
    ): FeedPreviewActivityViewModel {
        return ViewModelProviders.of(fragment, factory)[FeedPreviewActivityViewModel::class.java]
    }

}