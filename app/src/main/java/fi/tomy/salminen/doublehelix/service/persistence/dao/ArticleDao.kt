package fi.tomy.salminen.doublehelix.service.persistence.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import io.reactivex.Flowable


@Dao
abstract class ArticleDao {
    @Query("SELECT * FROM article")
    abstract fun getAll(): Flowable<List<ArticleEntity>>

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