package fi.tomy.salminen.doublehelix.binding


import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView

@BindingAdapter("adapter")
fun bindAdapter(recyclerView: RecyclerView, adapter : RecyclerView.Adapter<RecyclerView.ViewHolder>) {
    recyclerView.adapter = adapter
}
