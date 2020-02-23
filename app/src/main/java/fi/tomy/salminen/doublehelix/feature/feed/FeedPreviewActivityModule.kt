package fi.tomy.salminen.doublehelix.feature.feed

import androidx.lifecycle.ViewModelProviders
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository


@Module
abstract class FeedPreviewActivityModule {
    companion object {
        @Provides
        fun provideFeedFragmentViewModelFactory(
            subscriptionRepository: SubscriptionRepository,
            app: DoubleHelixApplication
        ): FeedPreviewActivityViewModel.Factory {
            return FeedPreviewActivityViewModel.Factory(subscriptionRepository, null, app)
        }

        @Provides
        fun provideFeedViewModel(
            activity: FeedPreviewActivity,
            factory: FeedPreviewActivityViewModel.Factory
        ): FeedPreviewActivityViewModel {
            return ViewModelProviders.of(activity, factory)[FeedPreviewActivityViewModel::class.java]
        }
    }

    @FragmentScope
    @ContributesAndroidInjector(modules = [FeedFragmentModule::class])
    abstract fun feedFragmentInjector(): FeedFragment
}