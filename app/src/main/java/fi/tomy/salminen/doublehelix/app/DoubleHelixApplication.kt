package fi.tomy.salminen.doublehelix.app

import fi.tomy.salminen.doublehelix.core.BaseApplication
import fi.tomy.salminen.doublehelix.inject.application.BaseApplicationModule
import fi.tomy.salminen.doublehelix.service.http.HttpServiceModule
import fi.tomy.salminen.doublehelix.service.persistence.PersistenceModule
import fi.tomy.salminen.doublehelix.service.rss.RssServiceModule


class DoubleHelixApplication : BaseApplication<DoubleHelixApplicationComponent>() {

    override fun inject() {
        component.inject(this)
    }

    override fun createComponent(): DoubleHelixApplicationComponent {
        return DaggerDoubleHelixApplicationComponent.builder()
            .baseApplicationModule(BaseApplicationModule(this))
            .httpServiceModule(HttpServiceModule())
            .rssServiceModule(RssServiceModule())
            .persistenceModule(PersistenceModule())
            .build()
    }
}