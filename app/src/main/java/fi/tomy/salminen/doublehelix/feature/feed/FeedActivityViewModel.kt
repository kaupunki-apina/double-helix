package fi.tomy.salminen.doublehelix.feature.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel


class FeedActivityViewModel(
    app: DoubleHelixApplication
) : BaseContextViewModel(app) {

    class Factory(
        val app: DoubleHelixApplication
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedActivityViewModel(app) as T
        }
    }
}
