package fi.tomy.salminen.doublehelix.feature.feed

import android.os.Bundle
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.core.BaseActivity
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule

import kotlinx.android.synthetic.main.activity_feed.*


class FeedActivity : BaseActivity<FeedActivityComponent>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed)
        setSupportActionBar(toolbar)
    }

    override fun createComponent(): FeedActivityComponent {
        return (application as DoubleHelixApplication).component.plus(DaggerFeedActivityComponent.builder()
            .feedActivityModule(FeedActivityModule())
            .baseActivityModule(BaseActivityModule(this))
            .build()
        )
    }

    override fun inject() {
        component.inject(this)
    }
}
