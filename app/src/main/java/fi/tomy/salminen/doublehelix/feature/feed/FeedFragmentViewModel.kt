package fi.tomy.salminen.doublehelix.feature.feed

import android.net.Uri
import androidx.lifecycle.*
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import io.reactivex.BackpressureStrategy
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit


class FeedFragmentViewModel(
    private val articleRepository: ArticleRepository,
    subscriptionRepository: SubscriptionRepository,
    app: DoubleHelixApplication,
    private val vmFactory: ArticleListItemViewModel.Factory,
    private val feedUri: Uri?
) : BaseContextViewModel(app) {
    private val isLoadingDebounce: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = mutableIsLoading

    private val mutableIsError: MutableLiveData<Boolean> = MutableLiveData(false)
    val isError: LiveData<Boolean> get() = mutableIsError

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
            LiveDataReactiveStreams.fromPublisher(articleRepository.getArticlesByUrl(feedUri)
                .toFlowable(BackpressureStrategy.LATEST)
                .doOnSubscribe {
                    mutableIsError.postValue(false)
                }
                .doOnError {
                    mutableIsError.postValue(true)
                }
                .onErrorReturn { emptyList() }
                .map { it.map { pair -> vmFactory.create(pair.first, pair.second) }
            })
        }
    }

    fun updateArticles() {
        compositeDisposable.add(articleRepository.updateArticles()
            .doOnSubscribe {
                isLoadingDebounce.onNext(true)
            }
            .doFinally {
                isLoadingDebounce.onNext(false)
            }
            .onErrorComplete()
            .subscribe())
    }

    class Factory(
        val articleRepository: ArticleRepository,
        private val subscriptionRepository: SubscriptionRepository,
        val app: DoubleHelixApplication,
        val vmFactory: ArticleListItemViewModel.Factory,
        val feedUri: Uri?
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedFragmentViewModel(articleRepository, subscriptionRepository, app, vmFactory, feedUri) as T
        }
    }
}