package fi.tomy.salminen.doublehelix.inject.fragment

import dagger.android.support.DaggerFragment
import fi.tomy.salminen.doublehelix.inject.viewmodel.ViewModelFactory
import javax.inject.Inject

abstract class BaseFragment : DaggerFragment() {
    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory
}