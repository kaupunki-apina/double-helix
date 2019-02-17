package fi.tomy.salminen.doublehelix.inject.application

import android.content.Context
import dagger.Component
import javax.inject.Named
import javax.inject.Singleton

@Component(modules = [BaseApplicationModule::class])
@Singleton
interface BaseApplicationComponent {

    val application: android.app.Application

    @get:ForApplication
    @Named("applicationContext")
    val applicationContext: Context

}