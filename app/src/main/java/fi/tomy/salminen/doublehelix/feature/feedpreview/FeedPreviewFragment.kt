package fi.tomy.salminen.doublehelix.feature.feedpreview

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.BaseFragment
import fi.tomy.salminen.doublehelix.feature.feed.ArticleListAdapter
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject


class FeedPreviewFragment : BaseFragment<FeedPreviewFragmentComponent>() {

    @Inject
    lateinit var viewModel: FeedPreviewFragmentViewModel

    @Inject
    lateinit var adapter: ArticleListAdapter

    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_feed_preview, container, false)
    }

    override fun createComponent(): FeedPreviewFragmentComponent {
        val uri = arguments?.getParcelable<Uri>(EXTRA_FEED_URI)
        return (activity as FeedPreviewActivity).component.plus(
            FeedPreviewFragmentModule(uri),
            BaseFragmentModule(this)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        feed_view.adapter = adapter
        feed_view.layoutManager = layoutManager

        viewModel.isLoading.observe(this, Observer {
            swipe_refresh.isRefreshing = it
        })

    }

    override fun inject() {
        component.inject(this)
    }

    companion object {
        val EXTRA_FEED_URI = "preview_feed_url"
        fun newInstance(): FeedPreviewFragment {
            return FeedPreviewFragment()
        }
    }
}