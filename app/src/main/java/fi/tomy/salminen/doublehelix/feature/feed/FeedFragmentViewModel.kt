package fi.tomy.salminen.doublehelix.feature.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.ArticleViewModel
import io.reactivex.disposables.CompositeDisposable

class FeedFragmentViewModel(private val articleRepository: ArticleRepository) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    fun getArticles(): LiveData<List<ArticleViewModel>> {
        val mutableLiveData: MutableLiveData<List<ArticleViewModel>> = MutableLiveData()

        articleRepository.updateArticles()

        compositeDisposable.add(
            articleRepository.getArticles()
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