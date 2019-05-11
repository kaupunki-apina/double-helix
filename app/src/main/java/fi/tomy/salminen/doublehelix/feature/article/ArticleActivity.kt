package fi.tomy.salminen.doublehelix.feature.article

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import fi.tomy.salminen.doublehelix.R

class ArticleActivity : AppCompatActivity() {
    companion object {
        val INTENT_ARTICLE_ID = "intent_article_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
    }
}
