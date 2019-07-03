package fi.tomy.salminen.doublehelix.inject.fragment


import android.content.Context
import dagger.Component
import fi.tomy.salminen.doublehelix.core.BaseFragment


@Component(modules = [BaseFragmentModule::class])
interface BaseFragmentComponent {
    @ForFragment
    fun fragmentContext(): Context

    fun fragment(): BaseFragment<*>

    @Component.Factory
    interface Factory {
        fun create(baseFragmentModule: BaseFragmentModule): BaseFragmentComponent
    }
}