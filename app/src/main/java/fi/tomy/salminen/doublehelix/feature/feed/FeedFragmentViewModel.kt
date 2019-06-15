package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository

class FeedFragmentViewModel(
    private val articleRepository: ArticleRepository,
    subscriptionRepository: SubscriptionRepository,
    app: Application
) :
    BaseContextViewModel(app) {

    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = mutableIsLoading

    init {
        compositeDisposable.addAll(subscriptionRepository.subscription.forEach {
            updateArticles()
        })
    }

    fun getArticles(): LiveData<List<ArticleDatabaseView>> {
        return articleRepository.getArticles()
    }

    fun updateArticles() {
        compositeDisposable.add(articleRepository.updateArticles()
            .doOnSubscribe {
                mutableIsLoading.postValue(true)
            }
            .doOnComplete {
                mutableIsLoading.postValue(false)
            }
            .subscribe())
    }

    class Factory(
        val articleRepository: ArticleRepository,
        private val subscriptionRepository: SubscriptionRepository,
        val app: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedFragmentViewModel(articleRepository, subscriptionRepository, app) as T
        }
    }
}