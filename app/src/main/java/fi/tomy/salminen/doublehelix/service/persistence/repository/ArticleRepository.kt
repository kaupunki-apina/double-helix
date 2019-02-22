package fi.tomy.salminen.doublehelix.service.persistence.repository

import fi.tomy.salminen.doublehelix.service.persistence.DoubleHelixDatabase
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.ArticleViewModel
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ArticleRepository @Inject constructor(database: DoubleHelixDatabase) {
    private val articleDao = database.articleDao()

    fun getWhere(feedId: Int): Flowable<List<ArticleViewModel>> {
        return articleDao.getWhere(feedId)
            .map { entities ->
                entities.map { entity ->
                    ArticleViewModel(entity)
                }
            }
    }
}