package fi.tomy.salminen.doublehelix.inject.activity

import android.content.Context
import dagger.Component
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplicationComponent

@ActivityScope
@Component(dependencies = [DoubleHelixApplicationComponent::class], modules = [BaseActivityModule::class])
interface BaseActivityComponent {

    val activity: android.app.Activity

    @get:ForActivity
    val activityContext: Context
}