package fi.tomy.salminen.doublehelix.feature.feed.service

import fi.tomy.salminen.doublehelix.feature.feed.viewmodel.SubscriptionViewModel
import fi.tomy.salminen.doublehelix.service.http.RssService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class RssSubscriptionService @Inject constructor(private val rssService: RssService){

    fun getSubscription(url: String): Observable<SubscriptionViewModel> {
        return rssService.getRssFeed(url)
            .observeOn(AndroidSchedulers.mainThread())
            .map { SubscriptionViewModel(it) }
    }
}