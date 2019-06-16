package fi.tomy.salminen.doublehelix.feature.feedpreview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.BaseFragment
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule


class FeedPreviewFragment : BaseFragment<FeedPreviewFragmentComponent>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_feed_preview, container, false)
    }

    override fun createComponent(): FeedPreviewFragmentComponent {
        return (activity as FeedPreviewActivity).component.plus(
            FeedPreviewFragmentModule(),
            BaseFragmentModule(this)
        )
    }

    override fun inject() {
        component.inject(this)
    }

    companion object {
        fun newInstance(): FeedPreviewFragment {
            return FeedPreviewFragment()
        }
    }
}