package fi.tomy.salminen.doublehelix.inject.activity

import android.view.MenuItem
import dagger.android.support.DaggerAppCompatActivity


abstract class BaseActivity: DaggerAppCompatActivity() {

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }
}
