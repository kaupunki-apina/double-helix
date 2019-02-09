package fi.tomy.salminen.doublehelix.inject.application

import android.content.Context
import dagger.Module
import javax.inject.Singleton
import dagger.Provides


@Module
class BaseApplicationModule(private val application: android.app.Application) {

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext(): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideApplication(): android.app.Application {
        return application
    }

}