package by.andersen.dobrov.newsapi.data.service

import by.andersen.dobrov.newsapi.data.model.NewsApiResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NewsService {

    @GET("v2/everything")
    suspend fun getNews(
        @Path(value = "q") query: String,
        @Path(value = "from") from: String,
        @Path(value = "sortBy") sortBy: String,
        @Path(value = "apiKey") apiKey: String,
    ): NewsApiResponse
}