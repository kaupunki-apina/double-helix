package fi.tomy.salminen.doublehelix.service.http

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import android.app.Application
import io.reactivex.schedulers.Schedulers
import okhttp3.Cache
import org.simpleframework.xml.convert.AnnotationStrategy
import org.simpleframework.xml.core.Persister
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import javax.inject.Singleton


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
    fun provideRssService(okHttpClient: OkHttpClient): RssService {
        return Retrofit.Builder()
            .baseUrl("http://null.com/") // Doesn't matter. Will be ignored later
            .client(okHttpClient)
            .addConverterFactory(SimpleXmlConverterFactory.createNonStrict(Persister(AnnotationStrategy())))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(RssService::class.java)
    }
}