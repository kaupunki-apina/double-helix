package fi.tomy.salminen.doublehelix.feature.article

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseContextViewModel
import fi.tomy.salminen.doublehelix.service.persistence.repository.ArticleRepository

class ArticleFragmentViewModel(
    private val articleRepository: ArticleRepository,
    app: Application
) : BaseContextViewModel(app) {

    private val mutableUrl: MutableLiveData<String> = MutableLiveData()
    val url: LiveData<String> get() = mutableUrl

    fun init(articleId: Int) {
        compositeDisposable.add(
            articleRepository.getArticleById(articleId)
                .doOnSuccess { mutableUrl.postValue(it.link) }
                .subscribe())
    }

    class Factory(val articleRepository: ArticleRepository, val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ArticleFragmentViewModel(articleRepository, app) as T
        }
    }
}