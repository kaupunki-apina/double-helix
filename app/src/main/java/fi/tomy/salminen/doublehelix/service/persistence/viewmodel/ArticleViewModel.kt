package fi.tomy.salminen.doublehelix.service.persistence.viewmodel

import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView


class ArticleViewModel(val article: ArticleDatabaseView) {
    val id: Int get() = article.id
    val title: String get() = article.title ?: ""
    val description: String get() = article.description ?: ""
    val publishDate: String get() = article.publishDate ?: ""
    val subscription: String get() = article.subscriptionDescription ?: ""
    val imageUrl: String? get() = article.imageUrl
}
