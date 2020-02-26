package fi.tomy.salminen.doublehelix.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import io.reactivex.disposables.CompositeDisposable


abstract class BaseContextViewModel(app: Application) : AndroidViewModel(app) {
    protected val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}