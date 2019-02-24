package fi.tomy.salminen.doublehelix.feature.feed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.BaseFragment
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject


class FeedFragment : BaseFragment<FeedFragmentComponent>() {

    @Inject
    lateinit var viewModel: FeedFragmentViewModel

    @Inject
    lateinit var adapter: ArticleListAdapter

    @Inject
    lateinit var layoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun createComponent(): FeedFragmentComponent {
        return (activity as FeedActivity).component.plus(
            FeedFragmentModule(),
            BaseFragmentModule(this)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        feed_view.adapter = adapter
        feed_view.layoutManager = layoutManager
        viewModel.getArticles().observe(this, Observer {
            if (it != null) {
                adapter.setArticles(it)
            }
        })
    }

    override fun inject() {
        component.inject(this)
    }

    companion object {
        fun newInstance(): FeedFragment {
            return FeedFragment()
        }
    }
}
