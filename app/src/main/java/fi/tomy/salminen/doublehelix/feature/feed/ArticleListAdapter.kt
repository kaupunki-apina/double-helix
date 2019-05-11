package fi.tomy.salminen.doublehelix.feature.feed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import fi.tomy.salminen.doublehelix.R
import fi.tomy.salminen.doublehelix.databinding.ListItemFeedBinding
import fi.tomy.salminen.doublehelix.service.persistence.databaseview.ArticleDatabaseView


class ArticleListAdapter(val vmFactory: ArticleListItemViewModel.Factory) :
    RecyclerView.Adapter<ArticleListAdapter.ArticleListItemBindingHolder>() {

    private var articles = emptyList<ArticleDatabaseView>()

    override fun onCreateViewHolder(parent: ViewGroup, index: Int): ArticleListItemBindingHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ArticleListItemBindingHolder(
            DataBindingUtil.inflate(
                inflater,
                R.layout.list_item_feed,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    override fun onBindViewHolder(viewHolder: ArticleListItemBindingHolder, index: Int) {
        viewHolder.bind(vmFactory.create(articles[index]))
    }

    fun setArticles(articles: List<ArticleDatabaseView>) {
        this.articles = articles
        notifyDataSetChanged()
    }

    inner class ArticleListItemBindingHolder(private val binding: ListItemFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(viewModel: ArticleListItemViewModel) {
            binding.articleViewModel = viewModel
            binding.executePendingBindings()
        }
    }
}
