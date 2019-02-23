package fi.tomy.salminen.doublehelix.feature.feed

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.FeedRepository
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.ArticleViewModel
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.FeedViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.CompositeDisposable

class FeedFragmentViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun getArticlesWhere(feedId: Int): LiveData<List<ArticleViewModel>> {
        val mutableLiveData: MutableLiveData<List<ArticleViewModel>> = MutableLiveData()

        articleRepository.updateArticles(feedId)

        compositeDisposable.add(
            articleRepository.getWhere(feedId)
                .subscribe {
                    mutableLiveData.postValue(it)
                })

        return mutableLiveData
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }

    class Factory(val articleRepository: ArticleRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedFragmentViewModel(articleRepository) as T
        }
    }
}