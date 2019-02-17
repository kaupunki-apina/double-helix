package fi.tomy.salminen.doublehelix.feature.feed

import android.arch.lifecycle.*
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import fi.tomy.salminen.doublehelix.service.persistence.repository.FeedRepository
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class FeedActivityViewModel (private val feedRepository: FeedRepository): ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val feed = feedRepository.feed

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun insertFeed(vararg feed: FeedEntity) {
        compositeDisposable.add(feedRepository
                .insertFeed(*feed)
                .subscribe())
    }

    class Factory(val feedRepository: FeedRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedActivityViewModel(feedRepository) as T
        }
    }
}