package fi.tomy.salminen.doublehelix.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


@BindingAdapter("icon")
fun bindIcon(fab: ExtendedFloatingActionButton, res: Int) {
    fab.setIconResource(res)
}
