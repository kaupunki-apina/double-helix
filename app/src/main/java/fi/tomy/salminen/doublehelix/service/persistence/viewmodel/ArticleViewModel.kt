package fi.tomy.salminen.doublehelix.service.persistence.viewmodel

import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView


class ArticleViewModel(val article: ArticleDatabaseView, val dateFormatter: DateFormatter) {
    val id: Int get() = article.id
    val title: String get() = article.title ?: ""
    val description: String get() = article.description ?: ""
    val publishDate: String get() = if (article.publishDate != null) dateFormatter.format(article.publishDate!!)  else ""
    val subscription: String get() = article.subscriptionDescription ?: ""
    val imageUrl: String? get() = article.imageUrl
}
