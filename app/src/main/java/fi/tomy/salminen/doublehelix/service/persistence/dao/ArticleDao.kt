package fi.tomy.salminen.doublehelix.service.persistence.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import io.reactivex.Flowable

@Dao
interface ArticleDao {
    @Query("SELECT * FROM article " +
            "LEFT JOIN subscription ON subscription.id = article.subscription_id " +
            "WHERE subscription.feed_id = :feedId")
    fun getWhere(feedId: Int): Flowable<List<ArticleEntity>>
}