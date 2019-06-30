package fi.tomy.salminen.doublehelix.inject.fragment


import androidx.fragment.app.Fragment
import dagger.Component


@Component(modules = [BaseFragmentModule::class])
interface BaseFragmentComponent {

    @get:FragmentScope
    val fragment: Fragment

}