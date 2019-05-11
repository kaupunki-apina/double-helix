package fi.tomy.salminen.doublehelix.feature.feed

import android.content.Intent
import android.view.View
import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.feature.article.ArticleActivity
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseViewModel
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView


class ArticleListItemViewModel(
    val dateFormatter: DateFormatter,
    val model: ArticleDatabaseView
) : BaseViewModel() {

    val id: Int get() = model.id
    val title: String? get() = model.title
    val description: String? get() = model.description
    val publishDate: String get() = if (model.publishDate != null) dateFormatter.format(model.publishDate) else ""
    val subscription: String? get() = model.subscriptionDescription
    val imageUrl: String? get() = model.imageUrl

    fun onClick(view: View) {
        view.context.startActivity(Intent(view.context, ArticleActivity::class.java).apply {
            putExtra(ArticleActivity.INTENT_ARTICLE_ID, id)
        })
    }

    class Factory(val dateFormatter: DateFormatter) {
        fun create(model: ArticleDatabaseView): ArticleListItemViewModel {
            return ArticleListItemViewModel(dateFormatter, model)
        }
    }
}