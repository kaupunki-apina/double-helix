package fi.tomy.salminen.doublehelix.binding

import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout


@BindingAdapter("spinnerColor")
fun bindTintColor(refreshLayout: SwipeRefreshLayout, res: Int) {
    refreshLayout.setColorSchemeColors(res)
}

@BindingAdapter("isRefreshing")
fun bindIsRefreshing(refreshLayout: SwipeRefreshLayout, isRefeshing: Boolean) {
    refreshLayout.isRefreshing = isRefeshing
}