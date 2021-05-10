package by.andersen.dobrov.newsapi.presentation.list.adapter

import androidx.recyclerview.widget.RecyclerView
import by.andersen.dobrov.newsapi.databinding.NewsListFragmentBinding
import by.andersen.dobrov.newsapi.domain.model.News
import coil.load
import kotlinx.android.synthetic.main.item_news_rv.view.*

class NewsViewHolder(
    private val itemBinding: NewsListFragmentBinding
) : RecyclerView.ViewHolder(itemBinding.root) {

    private val title get() = itemBinding.newsRv.news_rv_title
    private val image get() = itemBinding.newsRv.news_logo
    private val content get() = itemBinding.newsRv.news_rv_content
    private val author get() = itemBinding.newsRv.news_rv_content_author
    private val date get() = itemBinding.newsRv.news_rv_content_date


    fun onBind(article: News.Article) {

        title.text = article.title
        content.text = article.content
        author.text = article.author
        date.text = article.publishedAt

        image.load(article.urlToImage) {
            crossfade(true)

        }
    }


}