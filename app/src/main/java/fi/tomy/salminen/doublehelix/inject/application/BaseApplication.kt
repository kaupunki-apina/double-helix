package fi.tomy.salminen.doublehelix.inject.application

import android.app.Application
import androidx.annotation.CallSuper
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import fi.tomy.salminen.doublehelix.inject.Injector
import javax.inject.Inject


abstract class BaseApplication<T> : Application(), Injector<T>, HasAndroidInjector {

    @Inject
    lateinit var activityInjector: DispatchingAndroidInjector<Any>

    val component: T by lazy {
        createComponent()
    }

    @CallSuper
    override fun onCreate() {
        super.onCreate()
        inject()
    }

    override fun androidInjector(): AndroidInjector<Any> {
        return activityInjector
    }
}