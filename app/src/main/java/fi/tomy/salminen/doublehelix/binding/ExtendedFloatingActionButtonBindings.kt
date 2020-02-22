package fi.tomy.salminen.doublehelix.binding

import android.graphics.drawable.Animatable
import androidx.databinding.BindingAdapter
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton


@BindingAdapter("animateIcon")
fun bindIcon(fab: ExtendedFloatingActionButton, res: Int) {
    val animR: AnimatedVectorDrawableCompat? = AnimatedVectorDrawableCompat.create(fab.context, res)
    animR?.let {
        fab.icon = it
        (fab.icon as Animatable?)?.start()
    }
}
