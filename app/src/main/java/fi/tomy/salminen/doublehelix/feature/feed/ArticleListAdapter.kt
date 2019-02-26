package fi.tomy.salminen.doublehelix.feature.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.databinding.ListItemFeedBinding
import fi.tomy.salminen.doublehelix.service.persistence.viewmodel.ArticleViewModel


class ArticleListAdapter : RecyclerView.Adapter<ArticleListAdapter.ArticleViewHolder>() {

    private var articles = emptyList<ArticleViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): ArticleViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleViewHolder(DataBindingUtil.inflate(inflater, R.layout.list_item_feed, parent, false))
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(viewHolder: ArticleViewHolder, index: Int) {
        viewHolder.bind(articles[index])
    }

    fun setArticles(articles: List<ArticleViewModel>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    inner class ArticleViewHolder(private val binding: ListItemFeedBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(articleViewModel: ArticleViewModel) {
            binding.articleViewModel = articleViewModel
            binding.executePendingBindings()
        }
    }
}
