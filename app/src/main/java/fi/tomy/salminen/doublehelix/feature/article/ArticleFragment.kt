package fi.tomy.salminen.doublehelix.feature.article


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.BaseFragment
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import javax.inject.Inject


class ArticleFragment : BaseFragment<ArticleFragmentComponent>() {

    @Inject
    lateinit var viewModel: ArticleFragmentViewModel

    override fun createComponent(): ArticleFragmentComponent {
        return (activity as ArticleActivity).component.plus(
            ArticleFragmentModule(),
            BaseFragmentModule(this)
        )
    }

    override fun inject() {
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    companion object {
        fun newInstance(): ArticleFragment{
            return ArticleFragment()
        }
    }
}
