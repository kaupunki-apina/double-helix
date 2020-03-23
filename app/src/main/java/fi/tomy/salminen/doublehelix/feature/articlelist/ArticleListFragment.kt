package fi.tomy.salminen.doublehelix.feature.articlelist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.databinding.FragmentFeedBinding
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_feed.*
import javax.inject.Inject


class ArticleListFragment : BaseFragment<ArticleListFragmentViewModel>() {
    @Inject
    lateinit var adapter : ArticleListAdapter
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[ArticleListFragmentViewModel::class.java]
        binding.viewModel = viewModel
        feed_view.adapter = adapter
        feed_view.layoutManager = LinearLayoutManager(requireContext())
        viewModel.articles.observe(this, Observer {
            adapter.setArticles(it)
        })
    }

    companion object {
        fun newInstance(): ArticleListFragment {
            return ArticleListFragment()
        }
    }
}
