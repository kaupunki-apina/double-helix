package fi.tomy.salminen.doublehelix.inject.fragment


import android.content.Context
import android.support.v4.app.Fragment
import dagger.Module
import dagger.Provides


@Module
class BaseFragmentModule(val fragment: Fragment) {

    @Provides
    @FragmentScope
    fun provideFragment(): Fragment {
        return fragment
    }

    @Provides
    @FragmentScope
    @ForFragment
    internal fun provideFragmentContext(): Context {
        return fragment.requireContext()
    }
}