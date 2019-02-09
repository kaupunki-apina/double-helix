package fi.tomy.salminen.doublehelix.inject.fragment


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
}