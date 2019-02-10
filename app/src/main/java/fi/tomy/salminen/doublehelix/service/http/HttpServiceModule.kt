package fi.tomy.salminen.doublehelix.service.http

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton
import android.app.Application
import fi.tomy.salminen.doublehelix.inject.application.BaseApplicationModule
import okhttp3.Cache


@Module
class HttpServiceModule {

    @Provides
    @Singleton
    fun provideOkHttpCache(application: Application): Cache {
        val cacheSize: Long = 10 * 1024 * 1024 // 10 MiB
        return Cache(application.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
            .cache(cache)
            .build()
    }

    @Provides
    @Singleton
    fun provideIHttpService(okHttpClient: OkHttpClient) : IHttpService {
        return OkHttpService(okHttpClient)
    }
}