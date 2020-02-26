package fi.tomy.salminen.doublehelix.feature.mainfeed


import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProviders
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.common.ChromeCustomTabBinder
import fi.tomy.salminen.doublehelix.inject.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_feed.*
import javax.inject.Inject


class FeedActivity : BaseActivity<FeedActivityViewModel>() {

    @Inject
    lateinit var binder: ChromeCustomTabBinder

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val viewModel = ViewModelProviders.of(this, viewModelFactory)[FeedActivityViewModel::class.java]
        setContentView(R.layout.activity_feed)
        setSupportActionBar(bottom_bar)
        binder.bind(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_feed, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_feed_add -> {
                // Launch preview activity
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        binder.unbind(this)
    }
}
