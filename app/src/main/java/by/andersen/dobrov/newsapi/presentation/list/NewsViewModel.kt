package by.andersen.dobrov.newsapi.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import by.andersen.dobrov.newsapi.domain.NewsListRepository
import by.andersen.dobrov.newsapi.domain.model.News
import by.andersen.dobrov.newsapi.presentation.list.dto.RequestDTO
import by.andersen.dobrov.newsapi.util.BaseResult
import by.andersen.dobrov.newsapi.util.LoadingType
import by.andersen.dobrov.newsapi.util.ViewState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsViewModel(
    private val newsListRepository: NewsListRepository,
    private val context: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    private val _viewState = MutableLiveData<ViewState<List<News.Article>>>()
    private var getDataJob: Job? = null
    val viewState get() = _viewState


    fun onStart(requestDTO: RequestDTO) {
        fetchNews(requestDTO)
    }

    private fun fetchNews(requestDTO: RequestDTO) {
        checkMultipleCalls()

        getDataJob = viewModelScope.launch {

            _viewState.value = ViewState.Loading(LoadingType.Skeleton)

            when (val result = newsListRepository.getNews(requestDTO)) {

                is BaseResult.Success -> _viewState.postValue(
                    ViewState.Data(
                        data = result.data
                    )
                )
                is BaseResult.Failure -> _viewState.value = ViewState.Error(
                    error = result.error
                )
            }
        }
    }

    private fun checkMultipleCalls() {
        if (getDataJob?.isActive == true) return
    }
}