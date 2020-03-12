package fi.tomy.salminen.doublehelix.feature.feedpreview

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragment
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragmentModule
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.inject.fragment.FragmentScope
import io.reactivex.subjects.BehaviorSubject


@Module
abstract class FeedPreviewActivityModule {

    companion object {
        @Provides
        @ActivityScope
        fun provideIsValidUrl(): BehaviorSubject<Boolean> {
            return BehaviorSubject.createDefault(false)
        }

        @Provides
        fun provideDefaultUrl(activity: FeedPreviewActivity): String? {
            return activity.intent?.data?.toString()
        }

        @Provides
        @ActivityScope
        fun provideUrlSource(defaultString: String?): BehaviorSubject<String> {
            return if (defaultString != null) {
                BehaviorSubject.createDefault(defaultString)
            } else {
                BehaviorSubject.create()
            }
        }
    }

    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ArticleListFragmentModule::class,
            FeedPreviewToArticleListModule::class
        ]
    )
    abstract fun feedFragmentInjector(): ArticleListFragment
}