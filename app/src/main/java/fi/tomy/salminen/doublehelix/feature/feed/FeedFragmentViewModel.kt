package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import android.net.Uri
import androidx.lifecycle.*
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


class FeedFragmentViewModel(
    private val articleRepository: ArticleRepository,
    subscriptionRepository: SubscriptionRepository,
    app: Application,
    private val vmFactory: ArticleListItemViewModel.Factory,
    private val feedUri: Uri?
) : BaseContextViewModel(app) {
    private val isLoadingDebounce: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = mutableIsLoading

    init {
        compositeDisposable.addAll(
            subscriptionRepository.subscription
                .forEach {
                    updateArticles()
                },
            isLoadingDebounce
                .debounce(200, TimeUnit.MILLISECONDS)
                .forEach {
                    mutableIsLoading.postValue(it)
                }
        )
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
                isLoadingDebounce.onNext(true)
            }
            .doOnComplete {
                isLoadingDebounce.onNext(false)
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