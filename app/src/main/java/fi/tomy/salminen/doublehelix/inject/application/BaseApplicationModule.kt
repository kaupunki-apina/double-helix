package fi.tomy.salminen.doublehelix.inject.application

import android.content.Context
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import javax.inject.Singleton


// TODO Make this usable again :D
@Module
class BaseApplicationModule {

    @Provides
    @Singleton
    @ForApplication
    fun provideApplicationContext(application: DoubleHelixApplication): Context {
        return application.applicationContext
    }
}