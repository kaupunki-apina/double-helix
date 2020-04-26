package fi.tomy.salminen.doublehelix.feature.mainfeed


import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository
import fi.tomy.salminen.doublehelix.viewmodel.BaseViewModel
import javax.inject.Inject


class FeedActivityViewModel @Inject constructor(
    articleRepository: ArticleRepository
) : BaseViewModel() {

    init {
        compositeDisposable.add(
            articleRepository.updateArticles().subscribe()
        )
    }
}
