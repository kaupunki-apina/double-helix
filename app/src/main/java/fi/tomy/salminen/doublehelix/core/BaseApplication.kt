package fi.tomy.salminen.doublehelix.core

import android.support.annotation.CallSuper
import fi.tomy.salminen.doublehelix.inject.Injector
import android.app.Application


abstract class BaseApplication<T> : Application(), Injector<T> {

    private var component: T? = null

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        inject()
    }

    override fun component(): T {
        if (component == null) {
            component = createComponent()
        }
        return component as T
    }

    protected abstract fun createComponent(): T

}