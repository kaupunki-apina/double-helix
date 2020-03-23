package fi.tomy.salminen.doublehelix.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication


class DoubleHelixApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerDoubleHelixApplicationComponent.factory().create(this)
    }
}