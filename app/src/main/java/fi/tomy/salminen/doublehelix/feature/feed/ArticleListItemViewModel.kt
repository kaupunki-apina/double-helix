package fi.tomy.salminen.doublehelix.feature.feed


import android.net.Uri
import android.view.View
import androidx.browser.customtabs.CustomTabsIntent
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.common.DateFormatter
import fi.tomy.salminen.doublehelix.feature.viewmodel.BaseViewModel
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView


class ArticleListItemViewModel(
    val dateFormatter: DateFormatter,
    val model: ArticleDatabaseView
) : BaseViewModel() {

    val id: Int get() = model.id
    val title: String? get() = model.title
    val description: String? get() = model.description
    val publishDate: String get() = if (model.publishDate != null) dateFormatter.format(model.publishDate) else ""
    val subscription: String? get() = model.subscriptionDescription
    val imageUrl: String? get() = model.imageUrl
    val url: String? get() = model.link

    fun onClick(view: View) {
        CustomTabsIntent.Builder()
            .setToolbarColor(view.context.getColor(R.color.surface))
            .build()
            .launchUrl(view.context, Uri.parse(url))
    }

    class Factory(val dateFormatter: DateFormatter) {
        fun create(model: ArticleDatabaseView): ArticleListItemViewModel {
            return ArticleListItemViewModel(dateFormatter, model)
        }
    }
}