package fi.tomy.salminen.doublehelix.feature.feed

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.BaseActivity
import fi.tomy.salminen.doublehelix.core.FullScreenActivity
import fi.tomy.salminen.doublehelix.databinding.ActivityFeedPreviewBinding
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule
import fi.tomy.salminen.doublehelix.inject.activity.DaggerBaseActivityComponent
import kotlinx.android.synthetic.main.activity_feed_preview.*
import javax.inject.Inject


class FeedPreviewActivity : BaseActivity<FeedPreviewActivityComponent>() {
    @Inject
    lateinit var viewModel: FeedPreviewActivityViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding : ActivityFeedPreviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed_preview)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val contentFragment = supportFragmentManager.findFragmentById(R.id.feed_fragment)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

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
