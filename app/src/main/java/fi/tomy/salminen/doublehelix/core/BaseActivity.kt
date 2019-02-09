package fi.tomy.salminen.doublehelix.core

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import fi.tomy.salminen.doublehelix.inject.Injector


abstract class BaseActivity<T> : AppCompatActivity(), Injector<T> {

    private var component: T? = null

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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