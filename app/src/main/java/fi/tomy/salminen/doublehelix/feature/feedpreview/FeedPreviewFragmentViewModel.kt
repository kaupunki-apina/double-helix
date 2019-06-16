package fi.tomy.salminen.doublehelix.feature.feedpreview

import android.app.Application
import android.net.Uri
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository


class FeedPreviewFragmentViewModel(
    private val articleRepository: ArticleRepository,
    private val subscriptionRepository: SubscriptionRepository,
    val feedUri: Uri?,
    app: Application
) : BaseContextViewModel(app) {

    private val mutableIsLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = mutableIsLoading

    private val mutableFabIcon: MutableLiveData<Int> = MutableLiveData(R.drawable.ic_favorite_border_black_24dp)
    val fabIcon: LiveData<Int> get() = mutableFabIcon

    fun onFabClick(sender: View) {
        if (mutableFabIcon.value == R.drawable.ic_favorite_border_black_24dp) {
            mutableFabIcon.postValue(R.drawable.ic_favorite_black_24dp)
        } else {
            mutableFabIcon.postValue(R.drawable.ic_favorite_border_black_24dp)
        }
    }

    class Factory(
        private val articleRepository: ArticleRepository,
        private val subscriptionRepository: SubscriptionRepository,
        val feedUri: Uri?,
        private val app: Application
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedPreviewFragmentViewModel(articleRepository, subscriptionRepository,feedUri, app) as T
        }
    }
}