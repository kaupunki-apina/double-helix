package fi.tomy.salminen.doublehelix.feature.feed


import android.arch.lifecycle.Observer
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.BaseFragment
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import javax.inject.Inject


class FeedFragment : BaseFragment<FeedFragmentComponent>() {

    @Inject lateinit var viewModel: FeedFragmentViewModel

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

        if (arguments != null) {
            var feedId = arguments!!.getInt(FEED_ID)
            viewModel.getArticlesWhere(feedId).observe(this, Observer {
                // TODO
            })
        }
    }

    override fun inject() {
        component.inject(this)
    }

    companion object {
        private val FEED_ID = "arg_feed_id";

        fun newInstance(feedId: Int): FeedFragment {
            val fragment = FeedFragment()
            fragment.arguments = Bundle().apply {
                putInt(FEED_ID, feedId)
            }

            return fragment
        }
    }
}
