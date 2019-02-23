package fi.tomy.salminen.doublehelix.service.rss

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject


class RssSubscriptionService @Inject constructor(private val rssService: RssService){

    fun getSubscription(url: String): Flowable<RssModel> {
        return rssService.getRssFeed(url)
            .observeOn(AndroidSchedulers.mainThread())
    }
}