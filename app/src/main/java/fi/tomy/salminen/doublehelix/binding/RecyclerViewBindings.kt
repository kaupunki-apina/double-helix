package fi.tomy.salminen.doublehelix.binding


import android.graphics.Color
import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("backgroundColor")
fun bindAdapter(view: View, color : Color) {
    view.setBackgroundColor(color.toArgb())
}
