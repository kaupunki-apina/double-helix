package fi.tomy.salminen.doublehelix.core

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity
import fi.tomy.salminen.doublehelix.app.DoubleHelixApplication
import fi.tomy.salminen.doublehelix.inject.Injector
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.MenuItem
import android.view.MotionEvent
import android.content.Context.INPUT_METHOD_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T




abstract class BaseActivity<T> : AppCompatActivity(), Injector<T> {

    val component: T by lazy {
        createComponent()
    }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    val helixApplication get() = application as DoubleHelixApplication
}
