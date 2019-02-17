package fi.tomy.salminen.doublehelix.feature.feed

import android.arch.lifecycle.LiveData
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import fi.tomy.salminen.doublehelix.service.persistence.repository.FeedRepository
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class FeedViewModel {
    val feedRepository: FeedRepository
    lateinit var feed: LiveData<List<FeedEntity>>

    @Inject
    constructor(feedRepository: FeedRepository) {
        this.feedRepository = feedRepository
        var tmp = feedRepository.getFeed()
            .subscribeBy{
                feed = it
            }

        var tmp2 = feedRepository.insertFeed()
            .subscribe()
    }
}