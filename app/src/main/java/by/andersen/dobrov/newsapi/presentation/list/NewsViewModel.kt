package by.andersen.dobrov.newsapi.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.andersen.dobrov.newsapi.domain.NewsListRepository
import by.andersen.dobrov.newsapi.domain.model.News
import by.andersen.dobrov.newsapi.presentation.list.dto.RequestDTO
import by.andersen.dobrov.newsapi.util.BaseResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsListRepository: NewsListRepository,
    private val context: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _news = MutableLiveData<List<News.Article>>()

    val news = _news


    fun onStart(requestDTO: RequestDTO) = fetchNews(requestDTO)

    private suspend fun fetchNews(requestDTO: RequestDTO) = viewModelScope.launch {

        when (val result = newsListRepository.getNews(requestDTO)) {

            is BaseResult.Success -> _news.postValue(result.data)
            is BaseResult.Failure ->
        }
    }
}