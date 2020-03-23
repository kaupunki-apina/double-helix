package fi.tomy.salminen.doublehelix.app

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import fi.tomy.salminen.doublehelix.inject.activity.ActivityInjectionModule
import fi.tomy.salminen.doublehelix.service.http.HttpServiceModule
import fi.tomy.salminen.doublehelix.service.persistence.PersistenceModule
import fi.tomy.salminen.doublehelix.service.rss.RssServiceModule
import javax.inject.Singleton


@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        DoubleHelixApplicationModule::class,
        HttpServiceModule::class,
        RssServiceModule::class,
        PersistenceModule::class,
        ActivityInjectionModule::class
    ]
)
@Singleton
interface DoubleHelixApplicationComponent : AndroidInjector<DoubleHelixApplication> {

    @Component.Factory
    interface Factory : AndroidInjector.Factory<DoubleHelixApplication> {
        override fun create(
            @BindsInstance
            application: DoubleHelixApplication
        ): AndroidInjector<DoubleHelixApplication>
    }
}