package fi.tomy.salminen.doublehelix.inject.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Component
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplicationComponent


@ActivityScope
@Component(dependencies = [DoubleHelixApplicationComponent::class], modules = [BaseActivityModule::class])
interface BaseActivityComponent {

    val activity: AppCompatActivity

    @get:ForActivity
    val activityContext: Context
}