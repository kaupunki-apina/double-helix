package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.core.BaseViewModel


class FeedActivityViewModel(app: Application) : BaseViewModel(app) {

    class Factory(val app : Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedActivityViewModel(app) as T
        }
    }
}