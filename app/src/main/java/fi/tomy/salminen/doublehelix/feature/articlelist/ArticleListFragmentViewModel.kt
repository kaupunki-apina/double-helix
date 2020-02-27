package fi.tomy.salminen.doublehelix.feature.articlelist

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.viewmodel.BaseContextViewModel
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable
import javax.inject.Inject


class ArticleListFragmentViewModel @Inject constructor(
    app: DoubleHelixApplication,
    private val requestRefresh: Completable,
    articlesSource: Flowable<List<ArticleListItemViewModel>>
) : BaseContextViewModel(app) {
    var requestRefreshSubscription: Disposable? = null
    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = mutableIsLoading
    val articles = LiveDataReactiveStreams.fromPublisher(articlesSource)

    fun onRefresh() {
        requestRefreshSubscription.run {
            this?.let {
                if (!it.isDisposed) it.dispose()
            }

            requestRefreshSubscription = requestRefresh
                .doOnSubscribe { mutableIsLoading.postValue(true) }
                .doFinally { mutableIsLoading.postValue(false) }
                .subscribe()
                .also {
                    compositeDisposable.add(it)
                }
        }
    }
}