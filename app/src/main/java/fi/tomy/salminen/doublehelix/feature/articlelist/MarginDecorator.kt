package fi.tomy.salminen.doublehelix.feature.articlelist

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MarginDecorator(private val vertical: Int, private val horizontal: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView, state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = horizontal
            }
            left = vertical
            right = vertical
            bottom = horizontal
        }
    }
}