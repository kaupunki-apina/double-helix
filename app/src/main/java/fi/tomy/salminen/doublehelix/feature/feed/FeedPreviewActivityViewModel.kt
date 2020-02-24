package fi.tomy.salminen.doublehelix.feature.feed

import android.text.SpannableString
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject

class FeedPreviewActivityViewModel @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository,
    app: DoubleHelixApplication
) : BaseContextViewModel(app) {
    private val TAG = "FeedPreviewActivityViewModel"
    private val isSaved: BehaviorSubject<Boolean> = BehaviorSubject.createDefault(false)
    private val mutableFabIcon: MutableLiveData<Int> = MutableLiveData(R.drawable.avd_unfavourite)
    val fabIcon: LiveData<Int> get() = mutableFabIcon
    private var onClickDisposable: Disposable? = null

    private val mutableIsFabHidden: MutableLiveData<Boolean> = MutableLiveData(true)
    val isFabHidden: LiveData<Boolean> get () = mutableIsFabHidden

    private val focusSubject = BehaviorSubject.createDefault(false)
    private val searchTermSubject = BehaviorSubject.createDefault<CharSequence>("")

    init {
        compositeDisposable.addAll(
            /*
            subscriptionRepository.getSubsctiptionByUrl(feedUri.toString())
                .forEach {
                    isSaved.onNext(it.isEmpty())
                },
            */

            isSaved.observeOn(AndroidSchedulers.mainThread())
                .forEach {
                    val resId = if (it) R.drawable.avd_unfavourite
                    else R.drawable.avd_favourite
                    mutableFabIcon.value = resId
                },

            Observables.combineLatest(focusSubject, searchTermSubject)
                .forEach {
                    val searchSpan = SpannableString(it.second)

                    if (it.first) {
                        searchSpan.setSpan(
                            ForegroundColorSpan(app.getColor(R.color.primaryTextColor)),
                            0, searchSpan.length,
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE
                        )
                    } else {
                        searchSpan.setSpan(
                            ForegroundColorSpan(app.getColor(R.color.secondaryDarkColor)),
                            0, searchSpan.length,
                            Spanned.SPAN_INCLUSIVE_INCLUSIVE
                        )
                    }
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
        focusSubject.onNext(hasFocus)
    }

    fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        searchTermSubject.onNext(text)
    }
}