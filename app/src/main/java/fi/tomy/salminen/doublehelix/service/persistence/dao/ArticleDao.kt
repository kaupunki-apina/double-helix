package fi.tomy.salminen.doublehelix.service.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import io.reactivex.Flowable
import io.reactivex.Maybe


@Dao
abstract class ArticleDao {
    @Query("SELECT * FROM article_database_view ORDER BY datetime(publishDate) DESC")
    abstract fun getAll(): Flowable<List<ArticleDatabaseView>>

    @Query("SELECT * FROM article_database_view WHERE id = :articleId")
    abstract fun getWhereMaybe(articleId: Int): Maybe<ArticleDatabaseView>

    @Query("DELETE FROM article WHERE subscriptionId IN(:subscriptionId)")
    abstract fun deleteWhere(subscriptionId: IntArray)

    @Insert
    abstract fun insert(articles: List<ArticleEntity>)

    @Transaction
    open fun update(articles: List<ArticleEntity>) {
        deleteWhere(articles.map {
            it.subscriptionId
        }.distinct().toIntArray())
        insert(articles)
    }
}
