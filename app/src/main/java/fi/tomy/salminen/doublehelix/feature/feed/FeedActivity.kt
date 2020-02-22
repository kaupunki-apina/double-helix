package fi.tomy.salminen.doublehelix.feature.feed


import android.os.Bundle
import android.view.Menu
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.common.ChromeCustomTabBinder
import fi.tomy.salminen.doublehelix.core.BaseActivity
import fi.tomy.salminen.doublehelix.core.FullScreenActivity
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivityModule
import fi.tomy.salminen.doublehelix.inject.activity.DaggerBaseActivityComponent
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject


class FeedActivity : BaseActivity<FeedActivityComponent>() {
    @Inject
    lateinit var viewModel: FeedActivityViewModel

    @Inject
    lateinit var binder: ChromeCustomTabBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(bottom_bar)
        setContentView(R.layout.activity_feed)
        binder.bind(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_feed, menu)
        return true
    }

    override fun onStop() {
        super.onStop()
        binder.unbind(this)
    }

    override fun createComponent(): FeedActivityComponent {
        return DaggerFeedActivityComponent.factory().create(
            helixApplication.component,
            DaggerBaseActivityComponent.factory().create(BaseActivityModule(this)),
            FeedActivityModule()
        )
    }

    override fun inject() {
        component.inject(this)
    }
}
