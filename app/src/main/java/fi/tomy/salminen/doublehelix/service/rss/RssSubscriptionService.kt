package fi.tomy.salminen.doublehelix.service.rss

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class RssSubscriptionService @Inject constructor(private val rssService: RssServiceApi){

    fun getSubscription(url: String): Observable<RssModel> {
        return rssService.getRssFeed(url)
            .observeOn(AndroidSchedulers.mainThread())
    }
}