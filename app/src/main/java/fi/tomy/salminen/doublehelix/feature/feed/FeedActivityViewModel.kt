package fi.tomy.salminen.doublehelix.feature.feed

import android.arch.lifecycle.*
import fi.tomy.salminen.doublehelix.service.persistence.entity.FeedEntity
import fi.tomy.salminen.doublehelix.service.persistence.repository.FeedRepository
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.FeedViewModel
import io.reactivex.disposables.CompositeDisposable


class FeedActivityViewModel(private val feedRepository: FeedRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()
    val feed = MutableLiveData<List<FeedViewModel>>()

    init {
        compositeDisposable.add(
            feedRepository.feed.subscribe {
                feed.postValue(it)
            })
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    fun insertFeed(vararg feed: FeedEntity) {
        compositeDisposable.add(
            feedRepository
                .insertFeed(*feed)
                .subscribe()
        )
    }

    class Factory(val feedRepository: FeedRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedActivityViewModel(feedRepository) as T
        }
    }
}