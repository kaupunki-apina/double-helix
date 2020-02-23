package fi.tomy.salminen.doublehelix.app

import dagger.android.AndroidInjector
import fi.tomy.salminen.doublehelix.inject.application.BaseApplication


class DoubleHelixApplication : BaseApplication<AndroidInjector<DoubleHelixApplication>>() {

    override fun inject() {
        component.inject(this)
    }

    override fun createComponent(): AndroidInjector<DoubleHelixApplication> {
        return DaggerDoubleHelixApplicationComponent.factory().create(this)
    }
}