package by.andersen.dobrov.newsapi.domain

import by.andersen.dobrov.newsapi.domain.model.News.Article
import by.andersen.dobrov.newsapi.util.Response

interface NewsListRepository {

    suspend fun getNews(
        query: String,
        from: String,
        sortBy: String,
        apiKey: String,
    ): Response<List<Article>>
}