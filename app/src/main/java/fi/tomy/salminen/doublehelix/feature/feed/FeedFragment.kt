package fi.tomy.salminen.doublehelix.feature.feed


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.BaseFragment
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import fi.tomy.salminen.doublehelix.service.http.IHttpService
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject


class FeedFragment : BaseFragment<FeedFragmentComponent>() {

    @Inject
    lateinit var httpService: IHttpService

    companion object {
        fun newInstance() = FeedFragment()
    }

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

    override fun inject() {
        component.inject(this)
        httpService.getXml("http://www.nasa.gov/rss/dyn/educationnews.rss")
            .subscribeBy(
                onNext = { println(it) },
                onError =  { it.printStackTrace() },
                onComplete = { println("Done!") }
            )
    }
}
