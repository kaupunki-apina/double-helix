package fi.tomy.salminen.doublehelix.service.persistence

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.arch.persistence.room.Room
import fi.tomy.salminen.doublehelix.inject.application.ForApplication
import fi.tomy.salminen.doublehelix.service.persistence.seed.DatabaseSeed


@Module
class PersistenceModule {

    private val DB_NAME = "double_helix_database"

    @Provides
    @Singleton
    fun provideDatabaseSeed(application: android.app.Application): DatabaseSeed {
        return DatabaseSeed(application)
    }

    @Provides
    @Singleton
    fun provideDatabaseBuilder(@ForApplication applicationContext: Context, seed: DatabaseSeed): DoubleHelixDatabase {
        return Room.databaseBuilder(
            applicationContext,
            DoubleHelixDatabase::class.java,
            DB_NAME
        )
            .addCallback(seed)
            .build()
    }
}