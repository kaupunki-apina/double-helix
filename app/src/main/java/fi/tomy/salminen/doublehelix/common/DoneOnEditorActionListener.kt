package fi.tomy.salminen.doublehelix.common

import android.view.KeyEvent
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.TextView.OnEditorActionListener


internal class DoneOnEditorActionListener : OnEditorActionListener {
    override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            v?.let {
                it.clearFocus();
                val imm = it.context.getSystemService(InputMethodManager::class.java)
                imm.hideSoftInputFromWindow(it.windowToken, 0)
                return true
            }
        }
        return false
    }
}
