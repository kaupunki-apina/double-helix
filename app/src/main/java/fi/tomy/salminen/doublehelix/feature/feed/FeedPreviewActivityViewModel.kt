package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import android.net.Uri
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.BehaviorSubject

class FeedPreviewActivityViewModel(
    private val articleRepository: ArticleRepository,
    private val subscriptionRepository: SubscriptionRepository,
    val feedUri: Uri?,
    app: Application
) : BaseContextViewModel(app) {
    private val TAG = "FeedPreviewActivityViewModel"
    private val isSaved: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    private val mutableFabIcon: MutableLiveData<Int> = MutableLiveData(R.drawable.avd_unfavourite)
    val fabIcon: LiveData<Int> get() = mutableFabIcon
    private var onClickDisposable: Disposable? = null


    init {
        if (feedUri != null) {
            compositeDisposable.addAll(
                subscriptionRepository.getSubsctiptionByUrl(feedUri.toString())
                    .forEach {
                        isSaved.onNext(it.isEmpty())
                    },

                isSaved.observeOn(AndroidSchedulers.mainThread())
                    .forEach {
                        val resId = if (it) R.drawable.avd_unfavourite
                        else R.drawable.avd_favourite
                        mutableFabIcon.value = resId
                    }
            )
        }
    }

    fun onFabClick(sender: View) {
        if (feedUri == null) {
            Log.i(TAG, "Attempted to add a null feed uri. Ignoring.")
            return
        }

        // Cancel the previous in flight click handling.
        if (onClickDisposable?.isDisposed == false) {
            onClickDisposable?.dispose()
            onClickDisposable = null
        }

        val disposable = subscriptionRepository.getSubscriptionByUrlMaybe(feedUri.toString())
            .isEmpty
            .flatMapCompletable {
                if (it) {
                    subscriptionRepository.insertSubscription(SubscriptionEntity.from(feedUri.toString()))
                } else {
                    subscriptionRepository.deleteSubscriptionByUrl(feedUri.toString())
                }
            }
            .subscribe()

        compositeDisposable.add(disposable)
        onClickDisposable = disposable
    }

    class Factory(
        private val articleRepository: ArticleRepository,
        private val subscriptionRepository: SubscriptionRepository,
        val feedUri: Uri?,
        private val app: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedPreviewActivityViewModel(articleRepository, subscriptionRepository, feedUri, app) as T
        }
    }
}