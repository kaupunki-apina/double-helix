package fi.tomy.salminen.doublehelix.inject.fragment


import android.content.Context
import androidx.fragment.app.Fragment
import dagger.Module
import dagger.Provides


@Module
class BaseFragmentModule(val fragment: Fragment) {

    @Provides
    fun provideFragment(): Fragment {
        return fragment
    }

    @Provides
    @ForFragment
    internal fun provideFragmentContext(): Context {
        return fragment.requireContext()
    }
}