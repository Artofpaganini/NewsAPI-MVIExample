package by.andersen.dobrov.newsapi.domain

import by.andersen.dobrov.newsapi.domain.model.News.Article
import by.andersen.dobrov.newsapi.presentation.list.dto.RequestDTO
import by.andersen.dobrov.newsapi.util.BaseError
import by.andersen.dobrov.newsapi.util.BaseResult

interface NewsListRepository {

    suspend fun getNews(
        requestDTO: RequestDTO
    ): BaseResult<List<Article>, BaseError>
}