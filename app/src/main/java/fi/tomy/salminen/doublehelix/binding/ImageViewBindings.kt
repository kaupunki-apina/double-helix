package fi.tomy.salminen.doublehelix.binding

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


@BindingAdapter(
    value = [
        "imageUrl",
        "placeholder"
    ],
    requireAll = false
)
fun bindImageUrl(imageView: ImageView, url: String?, placeHolder: Drawable?) {
    var builder = Glide.with(imageView.context).load(url)

    if (placeHolder != null) {
        builder = builder.placeholder(placeHolder)
    }

    builder.into(imageView)
}