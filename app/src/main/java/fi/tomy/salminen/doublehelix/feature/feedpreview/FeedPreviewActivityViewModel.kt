package fi.tomy.salminen.doublehelix.feature.feedpreview

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.inject.activity.ActivityScope
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import fi.tomy.salminen.doublehelix.viewmodel.BaseViewModel
import io.reactivex.BackpressureStrategy
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.Observables
import io.reactivex.subjects.BehaviorSubject
import javax.inject.Inject


class FeedPreviewActivityViewModel @Inject constructor(
    private val subscriptionRepository: SubscriptionRepository,
    @ActivityScope private val urlSubject: BehaviorSubject<String>,
    @ActivityScope private val validUrlSubject: BehaviorSubject<Boolean>
) : BaseViewModel() {
    private var onClickDisposable: Disposable? = null
    private val isSaved: BehaviorSubject<Boolean> = BehaviorSubject.create()
    private val mutableFabIcon: MutableLiveData<Int> = MutableLiveData(R.drawable.avd_unfavourite)
    private val focusSubject = BehaviorSubject.createDefault(false)
    private val searchTermSubject = BehaviorSubject.create<String>()
    val hideFab: LiveData<Boolean> = LiveDataReactiveStreams.fromPublisher(
        Observables.combineLatest(focusSubject, validUrlSubject)
            .map { !(!it.first && it.second) }
            .distinctUntilChanged()
            .toFlowable(BackpressureStrategy.LATEST)
    )
    val fabIcon: LiveData<Int> get() = mutableFabIcon
    val url: LiveData<String> =
        LiveDataReactiveStreams.fromPublisher(urlSubject.toFlowable(BackpressureStrategy.LATEST))

    init {
        compositeDisposable.addAll(
            urlSubject.forEach {
                subscriptionRepository.getSubsctiptionByUrl(it)
                    .map { results ->
                        results.isEmpty()
                    }
                    .distinctUntilChanged()
                    .subscribe {
                        isSaved.onNext(it)
                    }
            },

            Observables.combineLatest(focusSubject, searchTermSubject)
                .filter { it.first.not() }
                .forEach { urlSubject.onNext(it.second) },


            isSaved.observeOn(AndroidSchedulers.mainThread())
                .forEach {
                    val resId = if (it) R.drawable.avd_unfavourite
                    else R.drawable.avd_favourite
                    mutableFabIcon.value = resId
                }
        )
    }

    fun onFabClick(sender: View) {
        url.value?.let { safeUrl ->
            // Cancel the previous in flight click handling.
            if (onClickDisposable?.isDisposed == false) {
                onClickDisposable?.dispose()
                onClickDisposable = null
            }

            // TODO Wrap in transaction
            val disposable = subscriptionRepository.getSubscriptionByUrlMaybe(safeUrl)
                .isEmpty
                .flatMapCompletable {
                    if (it) {
                        subscriptionRepository.insertSubscription(SubscriptionEntity.from(safeUrl))
                    } else {
                        subscriptionRepository.deleteSubscriptionByUrl(safeUrl)
                    }
                }
                .subscribe()

            compositeDisposable.add(disposable)
            onClickDisposable = disposable
        }
    }

    fun onFocusChange(v: View?, hasFocus: Boolean) {
        focusSubject.onNext(hasFocus)
    }

    fun onTextChanged(text: CharSequence, start: Int, before: Int, count: Int) {
        searchTermSubject.onNext(text.toString())
    }
}