package fi.tomy.salminen.doublehelix.feature.feed.service

import fi.tomy.salminen.doublehelix.feature.feed.viewmodel.FeedViewModel
import io.reactivex.Single
import javax.inject.Inject

class FeedService @Inject constructor(
    private val subscriptionService: RssSubscriptionService
) {
    fun getFeed(): Single<FeedViewModel> {
        return subscriptionService.getSubscription("http://www.nasa.gov/rss/dyn/educationnews.rss")
            .toList()
            .map { FeedViewModel(it) }
    }
}