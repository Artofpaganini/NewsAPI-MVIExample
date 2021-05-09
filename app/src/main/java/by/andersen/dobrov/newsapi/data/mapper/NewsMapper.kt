package by.andersen.dobrov.newsapi.data.mapper

import by.andersen.dobrov.newsapi.data.model.NewsApiResponse
import by.andersen.dobrov.newsapi.domain.model.News
import by.andersen.dobrov.newsapi.util.Mapper


class NewsMapper : Mapper<NewsApiResponse, List<News.Article>>() {

    override fun map(from: NewsApiResponse): List<News.Article> = from.articles.map {
        getNews(it)
    }


    private fun getNews(article: NewsApiResponse.Article) = News.Article(
        source = getSource(article.source),
        author = article.author,
        title = article.title,
        description = article.description,
        url = article.url,
        urlToImage = article.urlToImage,
        publishedAt = article.publishedAt,
        content = article.content,

        )

    private fun getSource(source: NewsApiResponse.Article.Source) = News.Article.Source(
        id = source.id,
        name = source.name,
    )

}