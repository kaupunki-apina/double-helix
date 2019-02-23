package fi.tomy.salminen.doublehelix.feature.feed


import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.FeedViewModel
import io.reactivex.Flowable
import io.reactivex.disposables.Disposable

class FeedPagerAdapter(fragmentManager: FragmentManager, feedFlow : Flowable<List<FeedViewModel>>) : FragmentStatePagerAdapter(fragmentManager) {
    var feed: List<FeedViewModel> = ArrayList()
    val subscription : Disposable

    init {
        subscription = feedFlow.subscribe {
            feed = it
            notifyDataSetChanged()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return feed[position].name
    }

    override fun getItem(position: Int): Fragment {
        return FeedFragment.newInstance(feed[position].id)
    }

    override fun getCount(): Int {
        return feed.size
    }
}