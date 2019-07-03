package fi.tomy.salminen.doublehelix.feature.feed

import android.os.Bundle
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.FullScreenActivity
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule
import fi.tomy.salminen.doublehelix.inject.activity.DaggerBaseActivityComponent

class FeedPreviewActivity : FullScreenActivity<FeedPreviewActivityComponent>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_preview)
        val contentFragment = supportFragmentManager.findFragmentById(R.id.feed_fragment)
        contentFragment?.arguments = Bundle().apply {
            putParcelable(FeedFragment.EXTRA_FEED_URI, intent?.data)
        }
    }

    override fun createComponent(): FeedPreviewActivityComponent {
        return DaggerFeedPreviewActivityComponent.factory().create(
            helixApplication.component,
            DaggerBaseActivityComponent.factory().create(BaseActivityModule(this)),
            FeedPreviewActivityModule(intent?.data)
        )
    }

    override fun inject() {
        component.inject(this)
    }
}
