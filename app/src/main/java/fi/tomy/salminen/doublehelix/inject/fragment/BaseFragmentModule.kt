package fi.tomy.salminen.doublehelix.inject.fragment


import android.content.Context
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.core.BaseFragment


@Module
class BaseFragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideFragment(): BaseFragment<*> {
        return fragment
    }

    @Provides
    @ForFragment
    internal fun provideFragmentContext(): Context {
        return fragment.requireContext()
    }
}