package fi.tomy.salminen.doublehelix.feature.article

import android.os.Bundle
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.core.BaseActivity
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule

class ArticleActivity : BaseActivity<ArticleActivityComponent>() {
    companion object {
        val INTENT_ARTICLE_ID = "intent_article_id"
    }

    override fun createComponent(): ArticleActivityComponent {
        return (application as DoubleHelixApplication).component.plus(
            ArticleActivityModule(),
            BaseActivityModule(this)
        )
    }

    override fun inject() {
        component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
    }
}
