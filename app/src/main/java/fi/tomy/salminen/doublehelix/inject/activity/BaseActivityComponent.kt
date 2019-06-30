package fi.tomy.salminen.doublehelix.inject.activity

import android.content.Context
import dagger.Component
import fi.tomy.salminen.doublehelix.core.BaseActivity


@Component(modules = [BaseActivityModule::class])
interface BaseActivityComponent {

    fun activity(): BaseActivity<*>

    @ForActivity
    fun activityContext(): Context

    @Component.Factory
    interface Factory {
        fun create(baseActivityModule: BaseActivityModule) : BaseActivityComponent
    }
}