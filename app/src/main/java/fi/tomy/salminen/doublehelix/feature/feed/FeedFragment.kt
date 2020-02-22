package fi.tomy.salminen.doublehelix.feature.feed


import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.BaseFragment
import fi.tomy.salminen.doublehelix.databinding.FragmentFeedBinding
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import fi.tomy.salminen.doublehelix.inject.fragment.DaggerBaseFragmentComponent
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject


class FeedFragment : BaseFragment<FeedFragmentComponent>() {

    @Inject
    lateinit var viewModel: FeedFragmentViewModel

    @Inject
    lateinit var adapter: ArticleListAdapter

    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    lateinit var binding : FragmentFeedBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed, container, true)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun createComponent(): FeedFragmentComponent {
        return DaggerFeedFragmentComponent.factory().create(
            helixActivity.helixApplication.component,
            DaggerBaseFragmentComponent.factory().create(BaseFragmentModule(this)),
            FeedFragmentModule(),
            arguments
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel
        feed_view.adapter = adapter
        feed_view.layoutManager = layoutManager
        viewModel.getArticles().observe(this, Observer {
            if (it != null) {
                adapter.setArticles(it)
            }
        })

        swipe_refresh.setOnRefreshListener {
            viewModel.updateArticles()
        }
    }

    override fun inject() {
        component.inject(this)
    }

    companion object {
        val EXTRA_FEED_URI = "feed_uri"
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }
}
