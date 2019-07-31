package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.*
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository


class FeedFragmentViewModel(
    private val articleRepository: ArticleRepository,
    subscriptionRepository: SubscriptionRepository,
    app: Application,
    private val vmFactory: ArticleListItemViewModel.Factory,
    private val feedUri: Uri?
) : BaseContextViewModel(app) {

    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = mutableIsLoading

    init {
        compositeDisposable.add(subscriptionRepository.subscription.forEach {
            updateArticles()
        })
    }

    fun getArticles(): LiveData<List<ArticleListItemViewModel>> {
        return if (feedUri == null) {
            Transformations.map<List<ArticleDatabaseView>, List<ArticleListItemViewModel>>(articleRepository.getArticles()) {
                it.map { article -> vmFactory.create(article) }
            }
        } else {
            LiveDataReactiveStreams.fromPublisher(articleRepository.getArticlesByUrl(feedUri).map {
                it.map { pair -> vmFactory.create(pair.first, pair.second)}
            })
        }
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
        val app: Application,
        val vmFactory: ArticleListItemViewModel.Factory,
        val feedUri: Uri?
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedFragmentViewModel(articleRepository, subscriptionRepository, app, vmFactory, feedUri) as T
        }
    }
}