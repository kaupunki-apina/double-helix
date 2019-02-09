package fi.tomy.salminen.doublehelix.inject.fragment


import android.support.v4.app.Fragment
import dagger.Component
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityComponent


@FragmentScope
@Component(dependencies = [BaseActivityComponent::class], modules = [BaseFragmentModule::class])
interface BaseFragmentComponent {

    @get:FragmentScope
    val fragment: Fragment

}