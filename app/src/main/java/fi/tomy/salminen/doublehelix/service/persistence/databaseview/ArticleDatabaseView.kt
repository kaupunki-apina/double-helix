package fi.tomy.salminen.doublehelix.service.persistence.databaseview

import androidx.room.DatabaseView
import java.time.ZonedDateTime


@DatabaseView(
    viewName = "article_database_view",
    value = "SELECT " +
            "article.*, " +
            "subscription.description AS subscriptionDescription " +
            "FROM article " +
            "LEFT JOIN subscription ON subscription.id = article.subscriptionId"
)
data class ArticleDatabaseView(
    val id: Int,
    val title: String?,
    val description: String?,
    val link: String?,
    val publishDate: ZonedDateTime?,
    val imageUrl: String?,
    val subscriptionId: Int,
    val subscriptionDescription: String?
)
