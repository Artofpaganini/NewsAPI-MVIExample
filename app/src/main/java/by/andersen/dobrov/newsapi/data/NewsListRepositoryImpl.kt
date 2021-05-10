package by.andersen.dobrov.newsapi.data

import by.andersen.dobrov.newsapi.data.mapper.NewsMapper
import by.andersen.dobrov.newsapi.data.service.NewsService
import by.andersen.dobrov.newsapi.domain.NewsListRepository
import by.andersen.dobrov.newsapi.domain.model.News.Article
import by.andersen.dobrov.newsapi.presentation.list.dto.RequestDTO
import by.andersen.dobrov.newsapi.util.BaseError
import by.andersen.dobrov.newsapi.util.BaseResult
import by.andersen.dobrov.newsapi.util.error.*


class NewsListRepositoryImpl(
    private val newsService: NewsService,
    private val newsMapper: NewsMapper,
) : NewsListRepository {

    override suspend fun getNews(
        requestDTO: RequestDTO,
    ): BaseResult<List<Article>, BaseError> = try {
        val response = newsService.getNews(
            query = requestDTO.query,
            from = requestDTO.from,
            sortBy = requestDTO.sortBy,
            apiKey = requestDTO.apiKey,
        )

        BaseResult.Success(newsMapper.map(response))
    } catch (e: Exception) {
        val lowLevelError = e.toBaseError()
        val apiError = ApiError(
            code = ApiErrorCode.LoadNews,
            underlying = lowLevelError
        )
        val domainError = DomainError(
            code = DomainErrorCode.LoadNews,
            underlying = apiError
        )
        BaseResult.Failure(error = domainError)
    }
}


