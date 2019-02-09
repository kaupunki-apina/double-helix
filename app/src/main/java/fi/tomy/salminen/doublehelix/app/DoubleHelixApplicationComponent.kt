package fi.tomy.salminen.doublehelix.app

import android.content.Context
import dagger.Component
import fi.tomy.salminen.doublehelix.feature.feed.FeedActivityComponent
import fi.tomy.salminen.doublehelix.feature.feed.FeedActivityModule
import fi.tomy.salminen.doublehelix.inject.application.ForApplication
import fi.tomy.salminen.doublehelix.inject.application.BaseApplicationComponent
import javax.inject.Singleton


@Component(modules = [DoubleHelixApplicationModule::class])
@Singleton
interface DoubleHelixApplicationComponent : BaseApplicationComponent {

    override val application: android.app.Application

    @get:ForApplication
    override val applicationContext: Context

    fun inject(application: DoubleHelixApplication)

    fun plus(module: FeedActivityComponent) : FeedActivityComponent
}