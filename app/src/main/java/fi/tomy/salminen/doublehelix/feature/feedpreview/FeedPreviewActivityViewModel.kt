package fi.tomy.salminen.doublehelix.feature.feedpreview

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import fi.tomy.salminen.doublehelix.viewmodel.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class FeedPreviewActivityViewModel @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository,
    private val app: DoubleHelixApplication,
    @ActivityScope private val urlSubject: BehaviorSubject<String>
) : BaseViewModel() {
    private val TAG = "FeedPreviewActivityViewModel"
    private val isSaved: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private val mutableFabIcon: MutableLiveData<Int> = MutableLiveData(R.drawable.avd_unfavourite)
    val fabIcon: LiveData<Int> get() = mutableFabIcon

    private val mutableIsFabHidden: MutableLiveData<Boolean> = MutableLiveData(true)
    val isFabHidden: LiveData<Boolean> get () = mutableIsFabHidden

    init {
        compositeDisposable.addAll(
            isSaved.observeOn(AndroidSchedulers.mainThread())
                .forEach {
                    val resId = if (it) R.drawable.avd_unfavourite
                    else R.drawable.avd_favourite
                    mutableFabIcon.value = resId
                }
        )
    }

    fun onFabClick(sender: View) {
        return
        /*
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
        */
    }

    fun onFocusChange(v: View?, hasFocus: Boolean) {
        // focusSubject.onNext(hasFocus)
    }

    fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        urlSubject.onNext(text.toString())
    }
}