package fi.tomy.salminen.doublehelix.feature.feedpreview

import android.os.Bundle
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.core.FullScreenActivity
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule

class FeedPreviewActivity : FullScreenActivity<FeedPreviewActivityComponent>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feed_preview)

        val uri = intent?.data
        if (uri != null) {
            /*
            viewModel.addSubscription(uri)
                .doOnComplete { Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show() }
                .doOnError { Toast.makeText(this, "Failure", Toast.LENGTH_SHORT).show() }
                .subscribe()
            */
        }
    }

    override fun createComponent(): FeedPreviewActivityComponent {
        return (application as DoubleHelixApplication).component.plus(
            FeedPreviewActivityModule(),
            BaseActivityModule(this)
        )
    }

    override fun inject() {
        component.inject(this)
    }
}
