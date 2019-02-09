package fi.tomy.salminen.doublehelix.inject.activity

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
class BaseActivityModule(private val activity: android.app.Activity) {

    @Provides
    @ActivityScope
    @ForActivity
    internal fun provideActivityContext(): Context {
        return activity
    }

    @Provides
    @ActivityScope
    internal fun provideActivity(): android.app.Activity {
        return activity
    }
}