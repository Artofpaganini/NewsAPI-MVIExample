package by.andersen.dobrov.newsapi.data

import by.andersen.dobrov.newsapi.data.mapper.NewsMapper
import by.andersen.dobrov.newsapi.data.service.NewsService
import by.andersen.dobrov.newsapi.domain.NewsListRepository
import by.andersen.dobrov.newsapi.domain.model.News.Article
import by.andersen.dobrov.newsapi.util.DateFormat
import by.andersen.dobrov.newsapi.util.DateFormatter
import by.andersen.dobrov.newsapi.util.Response

class NewsListRepositoryImpl(
    private val newsService: NewsService,
    private val newsMapper: NewsMapper,
    private val dateFormatter: DateFormatter,
) : NewsListRepository {

    override suspend fun getNews(
        query: String,
        from: String,
        sortBy: String,
        apiKey: String,
    ): Response<List<Article>> {
        return when (val response = newsService.getNews(
            query = query,
            from = from,
            sortBy = sortBy,
            apiKey = apiKey,
        )) {
            is Response.Success -> {

                response.data.articles.map {
                    formatDate(it.publishedAt)
                }

                Response.Success(newsMapper.map(response.data))
            }
            is Response.Failure -> Response.Failure(response.data)


        }
    }

    private fun formatDate(date: String) {
        val data = dateFormatter.parse(date, DateFormat.API_DATE_FORMAT)
        dateFormatter.format(data, DateFormat.UI_DATE_FORMAT)!!
    }
}

