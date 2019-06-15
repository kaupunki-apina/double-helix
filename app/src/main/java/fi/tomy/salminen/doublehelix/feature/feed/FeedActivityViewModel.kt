package fi.tomy.salminen.doublehelix.feature.feed

import android.app.Application
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import io.reactivex.Completable


class FeedActivityViewModel(
    app: Application,
    private val subscriptionRepository: SubscriptionRepository,
    private val articleRepository: ArticleRepository
) : BaseContextViewModel(app) {

    fun addSubscription(uri: Uri): Completable {
        return subscriptionRepository.getSubscriptionByUrl(uri.toString())
            .doOnComplete {
                subscriptionRepository.insertSubscription(SubscriptionEntity.fromUrl(uri.toString()))
                    .subscribe()
            }.ignoreElement()
    }

    class Factory(
        val app: Application,
        private val subscriptionRepository: SubscriptionRepository,
        private val articleRepository: ArticleRepository
    ) :
        ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return FeedActivityViewModel(app, subscriptionRepository, articleRepository) as T
        }
    }
}
