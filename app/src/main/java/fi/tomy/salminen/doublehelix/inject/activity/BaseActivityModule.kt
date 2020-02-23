package fi.tomy.salminen.doublehelix.inject.activity

import android.content.Context
import dagger.Module
import dagger.Provides


@Module
abstract class BaseActivityModule<T : BaseActivity> {

    @Provides
    @ForActivity
    fun provideActivityContext(activity: T): Context {
        return activity
    }

    @Provides
    fun provideActivity(activity: T): BaseActivity {
        return activity
    }
}