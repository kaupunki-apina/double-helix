package fi.tomy.salminen.doublehelix.feature.feed

import android.os.Bundle
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.core.BaseActivity
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule

import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject


class FeedActivity : BaseActivity<FeedActivityComponent>() {
    @Inject lateinit var feedViewModel : FeedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        setSupportActionBar(toolbar)
    }

    override fun createComponent(): FeedActivityComponent {
        return (application as DoubleHelixApplication).component.plus(
            FeedActivityModule(),
            BaseActivityModule(this)
        )
    }

    override fun inject() {
        component.inject(this)
    }
}
