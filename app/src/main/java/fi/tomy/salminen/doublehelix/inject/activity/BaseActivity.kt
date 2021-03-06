package fi.tomy.salminen.doublehelix.inject.activity

import android.view.MenuItem
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerAppCompatActivity
import fi.tomy.salminen.doublehelix.inject.viewmodel.ViewModelFactory
import javax.inject.Inject


abstract class BaseActivity<T: ViewModel>: DaggerAppCompatActivity() {

    @Inject
    protected lateinit var viewModelFactory: ViewModelFactory<T>

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
