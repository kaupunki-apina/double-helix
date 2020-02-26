package fi.tomy.salminen.doublehelix.feature.articlelist


import android.net.Uri
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.viewmodel.BaseViewModel
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView
import fi.tomy.salminen.doublehelix.service.persistence.entity.ArticleEntity
import fi.tomy.salminen.doublehelix.service.persistence.entity.SubscriptionEntity
import javax.inject.Inject


class ArticleListItemViewModel(
    val title: String?,
    val description: String?,
    val publishDate: String,
    val subscription: String?,
    val imageUrl: String?,
    val url: String?
) : BaseViewModel() {


    fun onClick(view: View) {
        CustomTabsIntent.Builder()
            .setToolbarColor(view.context.getColor(R.color.surface))
            .build()
            .launchUrl(view.context, Uri.parse(url))
    }

    class Factory @Inject constructor(val dateFormatter: DateFormatter) {
        fun create(model: ArticleDatabaseView): ArticleListItemViewModel {
            return ArticleListItemViewModel(
                model.title,
                model.description,
                if (model.publishDate != null) dateFormatter.format(model.publishDate) else "",
                model.subscriptionDescription,
                model.imageUrl,
                model.link
            )
        }

        fun create(subscriptionEntity: SubscriptionEntity, articleEntity: ArticleEntity): ArticleListItemViewModel {
            return ArticleListItemViewModel(
                articleEntity.title,
                articleEntity.description,
                articleEntity.publishDate?.let { dateFormatter.format(it) } ?: "",
                subscriptionEntity.description,
                articleEntity.imageUrl,
                articleEntity.link
            )
        }
    }
}