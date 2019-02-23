package fi.tomy.salminen.doublehelix.service.persistence.dao

import android.arch.persistence.room.*
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import io.reactivex.Flowable


@Dao
abstract class ArticleDao {
    @Query("SELECT * FROM article " +
            "LEFT JOIN subscription ON subscription.id = article.subscription_id " +
            "WHERE subscription.feed_id = :feedId")
    abstract fun getWhere(feedId: Int): Flowable<List<ArticleEntity>>

    @Query("DELETE FROM article WHERE subscription_id = :subscriptionId")
    abstract fun deleteWhere(subscriptionId: Int)

    @Insert
    abstract fun insert(articles: List<ArticleEntity>)

    @Transaction
    open fun update(subscriptionId: Int, articles: List<ArticleEntity>) {
        deleteWhere(subscriptionId)
        insert(articles)
    }
}