package fi.tomy.salminen.doublehelix.feature.mainfeed


import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.service.persistence.repository.SubscriptionRepository
import fi.tomy.salminen.doublehelix.viewmodel.BaseViewModel
import javax.inject.Inject


class FeedActivityViewModel @Inject constructor(
    articleRepository: ArticleRepository,
    subscriptionRepository: SubscriptionRepository
) : BaseViewModel() {

    init {
        compositeDisposable.addAll(
            articleRepository.updateArticles().subscribe(),

            subscriptionRepository.getUrls()
                .distinctUntilChanged()
                .forEach {
                    articleRepository.updateArticles()
                    .subscribe()
                }
        )
    }
}
