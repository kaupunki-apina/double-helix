package fi.tomy.salminen.doublehelix.inject.activity

import android.content.Context
import android.support.v7.app.AppCompatActivity
import dagger.Module
import dagger.Provides


@Module
class BaseActivityModule(private val activity: AppCompatActivity) {

    @Provides
    @ActivityScope
    @ForActivity
    internal fun provideActivityContext(): Context {
        return activity
    }

    @Provides
    @ActivityScope
    internal fun provideActivity(): AppCompatActivity {
        return activity
    }
}