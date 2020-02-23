package fi.tomy.salminen.doublehelix.service.persistence

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import fi.tomy.salminen.doublehelix.inject.application.ForApplication
import fi.tomy.salminen.doublehelix.service.persistence.seed.DatabaseSeed
import javax.inject.Singleton


@Module
class PersistenceModule {

    @Provides
    @Singleton
    fun provideDatabaseBuilder(@ForApplication applicationContext: Context, seed: DatabaseSeed): DoubleHelixDatabase {
        return Room.databaseBuilder(
            applicationContext,
            DoubleHelixDatabase::class.java,
            DoubleHelixDatabase.DB_NAME
        )
            .addCallback(seed)
            .build()
    }
}