package fi.tomy.salminen.doublehelix.binding

import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.ShapeDrawable
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("backgroundColor")
fun bindAdapter(view: View, color: Int) {
    if (view.background is ShapeDrawable) {
        (view.background as ShapeDrawable).paint.color = color
    } else if (view.background is GradientDrawable) {
        (view.background as GradientDrawable).setColor(color)
    } else {
        view.setBackgroundColor(color)
    }
}
