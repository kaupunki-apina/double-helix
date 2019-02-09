package fi.tomy.salminen.doublehelix.core

import android.support.annotation.CallSuper
import fi.tomy.salminen.doublehelix.inject.Injector
import android.app.Application


abstract class BaseApplication<T> : Application(), Injector<T> {

    val component: T by lazy {
        createComponent()
    }

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        inject()
    }
}