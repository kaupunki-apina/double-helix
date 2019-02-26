package fi.tomy.salminen.doublehelix.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.core.BaseViewModel


class FeedActivityViewModel : BaseViewModel() {

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedActivityViewModel() as T
        }
    }
}