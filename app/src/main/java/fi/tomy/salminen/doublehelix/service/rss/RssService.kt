package fi.tomy.salminen.doublehelix.service.rss

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Url

interface RssService {

    @GET
    fun getRssFeed(@Url url: String): Observable<RssModel>
}