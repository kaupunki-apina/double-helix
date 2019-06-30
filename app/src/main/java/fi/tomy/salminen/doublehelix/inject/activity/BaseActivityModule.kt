package fi.tomy.salminen.doublehelix.inject.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.core.BaseActivity


@Module
class BaseActivityModule(private val activity: BaseActivity<*>) {

    @Provides
    @ForActivity
    internal fun provideActivityContext(): Context {
        return activity
    }

    @Provides
    internal fun provideActivity(): BaseActivity<*> {
        return activity
    }
}