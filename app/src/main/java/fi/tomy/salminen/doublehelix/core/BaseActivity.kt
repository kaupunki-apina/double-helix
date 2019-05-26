package fi.tomy.salminen.doublehelix.core

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import fi.tomy.salminen.doublehelix.inject.Injector


abstract class BaseActivity<T> : AppCompatActivity(), Injector<T> {

    val component: T by lazy {
        createComponent()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }
}
