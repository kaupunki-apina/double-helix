package fi.tomy.salminen.doublehelix.feature.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.core.BaseViewModel
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.ArticleViewModel

class FeedFragmentViewModel(private val articleRepository: ArticleRepository) : BaseViewModel() {

    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = mutableIsLoading

    fun getArticles(): LiveData<List<ArticleViewModel>> {
        val mutableLiveData: MutableLiveData<List<ArticleViewModel>> = MutableLiveData()

        compositeDisposable.add(
            articleRepository.getArticles()
                .subscribe {
                    mutableLiveData.postValue(it)
                })

        return mutableLiveData
    }

    fun updateArticles() {
        compositeDisposable.add(articleRepository.updateArticles()
            .doOnSubscribe {
                mutableIsLoading.postValue( true)
            }
            .doOnComplete {
                mutableIsLoading.postValue(false)
            }
            .subscribe())
    }

    class Factory(val articleRepository: ArticleRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedFragmentViewModel(articleRepository) as T
        }
    }
}