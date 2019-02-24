package fi.tomy.salminen.doublehelix.service.persistence.viewmodel

import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity


class ArticleViewModel(val entity: ArticleEntity) {
    val id = entity.id

    val title : String get() = entity.title ?: ""
    val description : String get() = entity.description ?: ""
    val publishDate : String get() = entity.publishDate?: ""
}