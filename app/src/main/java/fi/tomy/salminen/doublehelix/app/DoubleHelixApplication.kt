package fi.tomy.salminen.doublehelix.app

import fi.tomy.salminen.doublehelix.core.BaseApplication
import fi.tomy.salminen.doublehelix.inject.application.BaseApplicationModule


class DoubleHelixApplication : BaseApplication<DoubleHelixApplicationComponent>() {

    override fun inject() {
        component.inject(this)
    }

    override fun createComponent(): DoubleHelixApplicationComponent {
        return DaggerDoubleHelixApplicationComponent.builder()
            .baseApplicationModule(
                BaseApplicationModule(this)
            )
            .build()
    }
}