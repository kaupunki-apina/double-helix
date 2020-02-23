package fi.tomy.salminen.doublehelix.inject.fragment

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
abstract class BaseFragmentModule<T : BaseFragment> {

    @Provides
    fun provideFragment(fragment: T): BaseFragment {
        return fragment
    }


    @Provides
    @ForFragment
    fun provideFragmentContext(fragment: T): Context {
        return fragment.requireContext()
    }
}