package fi.tomy.salminen.doublehelix.feature.article


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.lifecycle.Observer
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.core.BaseFragment
import fi.tomy.salminen.doublehelix.inject.fragment.BaseFragmentModule
import kotlinx.android.synthetic.main.fragment_article.*
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val articleId = activity?.intent?.getIntExtra(ArticleActivity.INTENT_ARTICLE_ID,  -1)

        article_webview.webViewClient = WebViewClient()
        article_webview.settings.domStorageEnabled = true
        article_webview.settings.javaScriptEnabled = true
        article_webview.settings.loadWithOverviewMode = true

        viewModel.url.observe(this, Observer {
            article_webview.loadUrl(it)
        })

        if (articleId != null) {
            viewModel.init(articleId)
        } else {
            // TODO Toast "could not load article" etc.
            activity?.finish()
        }
    }

    companion object {
        fun newInstance(): ArticleFragment{
            return ArticleFragment()
        }
    }
}
