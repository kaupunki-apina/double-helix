package fi.tomy.salminen.doublehelix.core

import android.os.Bundle
import android.view.View
import androidx.annotation.CallSuper

abstract class FullScreenActivity<T> : BaseActivity<T>() {

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_STABLE or
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}
