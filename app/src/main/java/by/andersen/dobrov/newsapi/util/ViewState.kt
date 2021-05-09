package by.andersen.dobrov.newsapi.util

sealed class ViewState<out T : Any> {
    data class Loading(val type: LoadingType) : ViewState<Nothing>()

    data class Data<out T : Any>(val data: T) : ViewState<T>()

    data class Error(val error: Throwable) : ViewState<Nothing>()
}

sealed class LoadingType {
    object Progress : LoadingType()
    object Skeleton : LoadingType()
}