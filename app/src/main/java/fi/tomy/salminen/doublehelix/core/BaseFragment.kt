package fi.tomy.salminen.doublehelix.core

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v4.app.Fragment
import fi.tomy.salminen.doublehelix.inject.Injector

abstract class BaseFragment<T> : Fragment(), Injector<T> {

    val component: T by lazy {
        createComponent()
    }

    @CallSuper
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        inject()
    }
}