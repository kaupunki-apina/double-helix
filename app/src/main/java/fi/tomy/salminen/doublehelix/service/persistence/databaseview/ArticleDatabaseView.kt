package fi.tomy.salminen.doublehelix.service.persistence.databaseview

import androidx.room.DatabaseView


@DatabaseView(
    viewName = "article_database_view",
    value ="SELECT " +
            "article.*, " +
            "subscription.description AS subscriptionDescription " +
            "FROM article " +
            "LEFT JOIN subscription ON subscription.id = article.subscriptionId"
)
data class ArticleDatabaseView(
    var id: Int,
    var title: String?,
    var description: String?,
    var link: String?,
    var publishDate: String?,
    var imageUrl: String?,
    var subscriptionId: Int,
    var subscriptionDescription: String?
)
