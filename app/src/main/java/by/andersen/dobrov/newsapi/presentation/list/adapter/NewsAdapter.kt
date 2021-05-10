package by.andersen.dobrov.newsapi.presentation.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.andersen.dobrov.newsapi.databinding.NewsListFragmentBinding.inflate
import by.andersen.dobrov.newsapi.domain.model.News

class NewsAdapter(
    private val data: List<News.Article>
) : RecyclerView.Adapter<NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding = inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) =
        holder.onBind(data[position])

    override fun getItemCount(): Int = data.size

}
