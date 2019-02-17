package fi.tomy.salminen.doublehelix.service.rss

import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
class RssServiceModule {

    @Provides
    @Singleton
    fun provideRssService(retrofit: Retrofit) : RssServiceApi {
        return retrofit.create(RssServiceApi::class.java)
    }
}