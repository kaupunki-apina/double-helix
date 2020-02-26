package fi.tomy.salminen.doublehelix.feature.feedpreview

import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.common.DoneOnEditorActionListener
import fi.tomy.salminen.doublehelix.databinding.ActivityFeedPreviewBinding
import fi.tomy.salminen.doublehelix.feature.articlelist.ArticleListFragment
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_feed_preview.*


class FeedPreviewActivity : BaseActivity<FeedPreviewActivityViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityFeedPreviewBinding = DataBindingUtil.setContentView(this, R.layout.activity_feed_preview)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[FeedPreviewActivityViewModel::class.java]
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        val contentFragment = supportFragmentManager.findFragmentById(R.id.feed_fragment)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        search_field.setOnEditorActionListener(DoneOnEditorActionListener())
        contentFragment?.arguments = Bundle().apply {
            putParcelable(ArticleListFragment.EXTRA_FEED_URI, intent?.data)
        }
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
}
