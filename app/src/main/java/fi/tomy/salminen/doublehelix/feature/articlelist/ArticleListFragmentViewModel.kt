package fi.tomy.salminen.doublehelix.feature.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import io.reactivex.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class ArticleListFragmentViewModel @Inject constructor(
    private val articleRepository: ArticleRepository,
    subscriptionRepository: SubscriptionRepository,
    app: DoubleHelixApplication,
    private val vmFactory: ArticleListItemViewModel.Factory
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
        // return if (feedUri == null) {
            return Transformations.map<List<ArticleDatabaseView>, List<ArticleListItemViewModel>>(articleRepository.getArticles()) {
                it.map { article -> vmFactory.create(article) }

            }
        /*
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
                .map {
                    it.map { pair -> vmFactory.create(pair.first, pair.second) }
                })
        }
        */
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
}