package fi.tomy.salminen.doublehelix.binding

import android.graphics.drawable.Animatable
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


@BindingAdapter("animateIcon")
fun bindIcon(efab: ExtendedFloatingActionButton, res: Int) {
    val animR: AnimatedVectorDrawableCompat? = AnimatedVectorDrawableCompat.create(efab.context, res)
    animR?.let {
        efab.icon = it
        (efab.icon as Animatable?)?.start()
    }
}

@BindingAdapter("hidden")
fun bindHidden(efab: ExtendedFloatingActionButton, hidden: Boolean) {
    if (hidden) {
        efab.hide()
    } else {
        efab.show()
    }
}
