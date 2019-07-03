package fi.tomy.salminen.doublehelix.app

import android.app.Application
import android.content.Context
import dagger.Component
import fi.tomy.salminen.doublehelix.feature.feed.FeedPreviewActivityComponent
import fi.tomy.salminen.doublehelix.feature.feed.FeedPreviewActivityModule
import fi.tomy.salminen.doublehelix.inject.application.BaseApplicationComponent
import fi.tomy.salminen.doublehelix.inject.application.ForApplication
import fi.tomy.salminen.doublehelix.service.http.HttpServiceModule
import fi.tomy.salminen.doublehelix.service.persistence.PersistenceModule
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import fi.tomy.salminen.doublehelix.service.persistence.seed.SeedComponent
import fi.tomy.salminen.doublehelix.service.persistence.seed.SeedModule
import fi.tomy.salminen.doublehelix.service.rss.RssServiceModule
import javax.inject.Singleton


@Component(
    modules = [
        DoubleHelixApplicationModule::class,
        HttpServiceModule::class,
        RssServiceModule::class,
        PersistenceModule::class
    ]
)
@Singleton
interface DoubleHelixApplicationComponent : BaseApplicationComponent {

    override val application: Application

    @get:ForApplication
    override val applicationContext: Context

    fun inject(application: DoubleHelixApplication)

    fun provideSubscriptionRepository(): SubscriptionRepository

    fun provideArticleRepository(): ArticleRepository

    fun plus(seedModule: SeedModule): SeedComponent
}