package fi.tomy.salminen.doublehelix.feature.feed

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.common.DoneOnEditorActionListener
import fi.tomy.salminen.doublehelix.core.BaseActivity
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

        search_field.setOnEditorActionListener(DoneOnEditorActionListener())
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

    /**
     * Clears focus from search field when there is a touch event outside the view bounds
     */
    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (search_field.isFocused) {
                val outRect = Rect()
                search_field.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    search_field.clearFocus()
                    val imm = getSystemService(InputMethodManager::class.java)
                    imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    override fun inject() {
        component.inject(this)
    }
}
